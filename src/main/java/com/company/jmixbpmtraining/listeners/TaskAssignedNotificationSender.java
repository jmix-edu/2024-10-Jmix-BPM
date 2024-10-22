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

import com.company.jmixbpmtraining.entity.User;
import io.jmix.bpm.engine.events.UserTaskAssignedEvent;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.notifications.NotificationManager;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskAssignedNotificationSender {
    
    @Autowired
    private DataManager dataManager;

    @Autowired
    private SystemAuthenticator authenticator;
    
    @Autowired
    protected NotificationManager notificationManager;

    @EventListener
    public void onTaskAssigned(UserTaskAssignedEvent event) {
        Task task = event.getTask();
        User user = null;
        authenticator.begin();
        try {
            user = dataManager.load(User.class)
                    .query("select u from jbt_User u where u.username = :username")
                    .parameter("username", event.getUsername())
                    .one();
        } catch (Exception e) {
            //ignore
        } finally {
            authenticator.end();
        }
        notificationManager.createNotification()
                .withSubject("New task")
                .withRecipients(user)
                .toChannelsByNames("in-app")
                .withPlainTextContentType()
                .withTypeName("task")
                .withBody("A new task  [" + task.getName() + "] is assigned to you")
                .send();
    }
}
