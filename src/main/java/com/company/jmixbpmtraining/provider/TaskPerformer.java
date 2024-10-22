package com.company.jmixbpmtraining.provider;

import io.jmix.bpm.provider.UserProvider;

@UserProvider(value = "jbt_TaskPerformer")
public class TaskPerformer {

    /**
     * This method returns username
     */
    public String getUser() {
        return "jane";
    }
}