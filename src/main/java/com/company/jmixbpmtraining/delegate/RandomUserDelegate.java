/*
 * Copyright (c) 2023. Haulmont.
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

package com.company.jmixbpmtraining.delegate;

import com.company.jmixbpmtraining.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "jbt_RandomUserDelegate")
public class RandomUserDelegate implements JavaDelegate {
    
    @Autowired
    private SystemAuthenticator authenticator;
    
    @Autowired
    private DataManager dataManager;
    
    @Override
    public void execute(DelegateExecution execution) {
        List<User> userList;
        Long randomIndex = (Long) execution.getVariable("randomIndex");
        authenticator.begin();
        try {
            userList = dataManager.load(User.class).all().list();
        } finally {
            authenticator.end();
        }
        String username = userList.get(Math.toIntExact(randomIndex))
                .getUsername().toUpperCase();
        
        String message = (String) execution.getVariable("message");
        execution.setVariable("message", username + ", " + message);
        
        System.out.println("Service task: " + execution.getCurrentActivityId() +
                ", username: " + username);
    }

    public void print() {
        System.out.println("Print method");
    }
}