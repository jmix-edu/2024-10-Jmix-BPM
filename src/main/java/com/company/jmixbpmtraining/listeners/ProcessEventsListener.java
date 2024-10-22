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

package com.company.jmixbpmtraining.listeners;

import io.jmix.bpm.engine.events.ProcessCompletedEvent;
import io.jmix.bpm.engine.events.ProcessStartedEvent;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessEventsListener {
    
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProcessEventsListener.class);
    
    @EventListener
    public void onProcessStart(ProcessStartedEvent event){
        String key = event.getProcessDefinition().getKey();
        log.info("Process started: {}", key);
    }

    @EventListener
    public void onProcessCompleted(ProcessCompletedEvent event){
        String key = event.getProcessDefinition().getKey();
        log.info("Process completed: {}", key);
    }
}
