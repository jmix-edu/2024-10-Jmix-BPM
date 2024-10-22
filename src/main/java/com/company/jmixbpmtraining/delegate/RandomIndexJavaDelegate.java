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

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Random;


@SuppressWarnings("JmixUsingLogger")
public class RandomIndexJavaDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        
        long numberOfUsers = (long) execution.getVariable("numberOfUsers");
        long randomIndex = new Random().nextLong(numberOfUsers);
        execution.setVariable("randomIndex", randomIndex);
        
        System.out.println("Service task: " + execution.getCurrentActivityId()
                + ", randomIndex: " + randomIndex);
    }
}