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

import org.flowable.engine.delegate.BpmnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Thread.sleep;

@Component("jbt_ErrorService")
public class ErrorService {

    private static final Logger log = LoggerFactory.getLogger(ErrorService.class);
    public static final int MAX_DELAY = 3000;
    public static final int PROBABILITY_FAIL = 50;
    
    public void delay() {
        Random random = new Random();
        int mills = random.nextInt(MAX_DELAY);
        try {
            sleep(mills);
        } catch (InterruptedException ignored) {
        }
    }
    
    public boolean failure() {
        Random random = new Random();
        return random.nextInt(100) < PROBABILITY_FAIL;
    }
    
    public void bpmnError(String code) {
        log.info("Error code: {}", code);
        throw new BpmnError(code);
    }
    
    public void bpmnErrorProbable(String code) {
        if(!failure()) return;
        log.info("BPMN error: {}", code);
        throw new BpmnError(code);
    }
    
    public void technicalError(String code, Boolean error) {
        if (error != null && !error) return;

        log.info("Technical Error: {}", code);
        throw new RuntimeException(code);
    }
    
    public void probableError(String code) {
        if (failure()) {
            technicalError(code, true);
        }
        if (failure()) {
            bpmnError(code);
        }
    }
}

