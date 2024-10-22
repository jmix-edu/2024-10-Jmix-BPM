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
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("ALL")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UpperCaseJavaDelegate implements JavaDelegate {

    private Expression messageField;
    private Expression quantityField;
    @Override
    public void execute(DelegateExecution execution) {
        
        String message = (String) messageField.getValue(execution);
        String str = (String) quantityField.getValue(execution);
        int quantity = Integer.parseInt(str);

        String upperCaseMessage = message.toUpperCase();
        execution.setVariable("message", upperCaseMessage );

        for (int i = 0; i < quantity; i++)
            System.out.println(upperCaseMessage);
    }
}