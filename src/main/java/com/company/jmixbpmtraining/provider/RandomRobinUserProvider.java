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

package com.company.jmixbpmtraining.provider;

import com.company.jmixbpmtraining.entity.User;
import io.jmix.bpm.provider.UserProvider;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@UserProvider(value = "jbt_RandomRobinUserProvider")
public class RandomRobinUserProvider {
    
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RandomRobinUserProvider.class);
    @Autowired
    private DataManager dataManager;
    
    @Autowired
    private SystemAuthenticator authenticator;
    
    public String getUser() {
        authenticator.begin();
        try {
            List<User> accountants = dataManager.load(User.class)
                    .query("SELECT u from jbt_User u WHERE u.position = 'accountant'")
                    .list();
            int randomNumber = new Random().nextInt(accountants.size());
            String username = accountants.get(randomNumber).getUsername();
            log.info("User provider result: {}",  username);
            return username;
        } finally {
          authenticator.end();
        }
    }
}