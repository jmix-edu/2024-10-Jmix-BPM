package com.company.jmixbpmtraining.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.now;

@Component("jbt_GreetingMessageBean")
public class GreetingMessageBean {

    public LocalTime printMessage(String greeting,
                                  String message,
                                  DelegateExecution execution)
    {
        String currentActivityId = execution.getCurrentActivityId();
        System.out.println("Service task: " + currentActivityId);
        System.out.println(greeting + "! "  + message);
        return now();
    }

    public void printTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = time.format(formatter);
        System.out.println("Time is: " + formattedTime);
    }
}
