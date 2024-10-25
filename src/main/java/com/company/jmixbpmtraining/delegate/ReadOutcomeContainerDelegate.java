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

package com.company.jmixbpmtraining.delegate;

import io.jmix.bpm.data.outcome.Outcome;
import io.jmix.bpm.data.outcome.OutcomesContainer;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("jbt_ReadOutcomeContainer")
public class ReadOutcomeContainerDelegate implements JavaDelegate {

    private Expression taskId;
    
    @Override
    public void execute(DelegateExecution execution) {
        String taskIdValue = (String) taskId.getValue(execution);
        String outcomeContainerName = taskIdValue + "_result";
        OutcomesContainer result = (OutcomesContainer) execution
                .getVariable(outcomeContainerName);

        List<Outcome> outcomes = result.getOutcomes();
        StringBuilder sb = new StringBuilder();
        for (Outcome outcome : outcomes) {
            sb.append(outcome.getUser()).append(" - ")
                    .append(outcome.getOutcomeId()).append(" - ")
                    .append(outcome.getDate().toString()).append("\n");
        }
        Map<String, Object> variables = execution.getVariables();
        Map<String, Object> variablesLocal = execution.getVariablesLocal();
        execution.setVariable("outcomes", sb.toString());
    }
}
