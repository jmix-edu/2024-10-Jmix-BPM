/*
 * Copyright (c) 2024. Haulmont.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.company.jmixbpmtraining.service;

import com.company.jmixbpmtraining.entity.Account;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.flowable.engine.delegate.BpmnError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Thread.sleep;

@Component("jbt_AccountService")
public class AccountService {
    
    public static final int MAX_DELAY = 3000;
    public static final int PROBABILITY_FAIL = 10;
    @Autowired
    private DataManager dataManager;
    
    @Autowired
    private SystemAuthenticator authenticator;
    
    public boolean debit(String owner, Long amountToDebit) {
        delay();
        if(failure()) {
            System.out.println("BusinessExceptionOccurred");
            throw new BpmnError("BusinessExceptionOccurred");
        }

        authenticator.begin();
        try {
            Account account = dataManager.load(Account.class)
                    .query("select a from jbt_Account a where a.owner = :owner")
                    .parameter("owner", owner)
                    .one();
            long newAmount = account.getAmount() - amountToDebit;
            if (newAmount < 0L) {
                System.out.println("ERROR: Can't debit account " + owner);
                return false;
            } else {
                account.setAmount(newAmount);
                dataManager.save(account);
                System.out.println("SUCCESS: Debit account " + owner + " by " + amountToDebit);
                return true;
            }
        } finally {
            authenticator.end();
        }
    }
    
    public boolean credit(String owner, Long amountToCredit) {
        delay();
        if(failure()) {
            System.out.println("BusinessExceptionOccurred");
            throw new BpmnError("BusinessExceptionOccurred");
        }
        
        authenticator.begin();
        try {
            Account account = dataManager.load(Account.class)
                    .query("select a from jbt_Account a where a.owner = :owner")
                    .parameter("owner", owner)
                    .one();
            long newAmount = account.getAmount() + amountToCredit;
            if (newAmount > 10000L) {
                System.out.println("ERROR: Can't credit account " + owner);
                return false;
            } else {
                account.setAmount(newAmount);
                dataManager.save(account);
                System.out.println("SUCCESS: Credit account " + owner + " by " + amountToCredit);
                return true;
            }
        } finally {
            authenticator.end();
        }
    }
    
    private void delay() {
        Random random = new Random();
        int mills = random.nextInt(MAX_DELAY);
        try {
            sleep(mills);
        } catch (InterruptedException ignored) {}
    }
    
    private boolean failure() {
        Random random = new Random();
        return random.nextInt(100) < PROBABILITY_FAIL;
    }
    
}
