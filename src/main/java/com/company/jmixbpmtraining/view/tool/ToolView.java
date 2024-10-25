package com.company.jmixbpmtraining.view.tool;


import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.eventsubscription.api.EventSubscription;
import org.flowable.job.api.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Route(value = "tool-view", layout = MainView.class)
@ViewController("jbt_ToolView")
@ViewDescriptor("tool-view.xml")
public class ToolView extends StandardView {

    private static final Logger log = LoggerFactory.getLogger(ToolView.class);
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private Notifications notifications;

    @ViewComponent
    private TypedTextField<String> messageField;
    @ViewComponent
    private TypedTextField<String> processInstanceIdField;
    @ViewComponent
    private TypedTextField<String> signalField;
    @ViewComponent
    private TypedTextField<String> executionField;

    private static final String NO_PROC_DEF = "No process definition to start by this message";
    private static final String NO_SUBS_MESSAGE = "No subscriptions to this message";
    private static final String NO_SUBS_SIGNAL = "No subscriptions to this signal";
    private static final String NO_EXEC_MESSAGE = "No execution waiting this message";
    @ViewComponent
    private TypedTextField<String> procDefField;

    @Subscribe(id = "sendMessageBtn", subject = "clickListener")
    public void onSendMessageBtnClick(final ClickEvent<JmixButton> event) {
        String messageName = messageField.getValue();
        try {
            runtimeService.startProcessInstanceByMessage(messageName);
        } catch (Exception e) {
            log.info(NO_PROC_DEF);
        }
    }

    @Subscribe(id = "startBySignalBtn", subject = "clickListener")
    public void onStartBySignalBtnClick(final ClickEvent<JmixButton> event) {
//        runtimeService.createSignalEvent(signalName).send();
    }

    @Subscribe(id = "sendToProcessInstanceBtn", subject = "clickListener")
    public void onSendToProcessInstanceBtnClick(final ClickEvent<JmixButton> event) {
        String messageName = messageField.getValue();
        String executionId = executionField.getTypedValue();
        try {
            runtimeService.messageEventReceived(messageName, executionId);
        } catch (Exception e) {
            log.info("No process instance subscribed to this message");
        }
    }

    @Subscribe(id = "getSubscriptionsBtn", subject = "clickListener")
    public void onGetSubscriptionsBtnClick(final ClickEvent<JmixButton> event) {
        String messageName = messageField.getValue();
        try {
            List<ProcessDefinition> processDefinitions = repositoryService
                    .createProcessDefinitionQuery()
                    .messageEventSubscriptionName(messageName)
                    .list();

            StringBuilder sb = new StringBuilder();
            processDefinitions.stream()
                    .map(ProcessDefinition::getName)
                    .forEach(s -> sb.append(s).append("\n "));

            notifications.show(sb.toString());
            log.info(sb.toString());
        } catch (Exception e) {
            log.info(NO_SUBS_MESSAGE);
        }
    }

    @Subscribe(id = "throwSignalBtn", subject = "clickListener")
    public void onThrowSignalBtnClick(final ClickEvent<JmixButton> event) {
        String signalName = signalField.getValue();
        try {
            runtimeService.signalEventReceived(signalName);
        } catch (Exception e) {
            log.info(NO_SUBS_SIGNAL);
        }
    }

    @Subscribe(id = "throwSignalToExecutionBtn", subject = "clickListener")
    public void onThrowSignalToExecutionBtnClick(final ClickEvent<JmixButton> event) {
        String signalName = signalField.getTypedValue();
        String executionId = executionField.getTypedValue();
        try {
            runtimeService.signalEventReceived(signalName, executionId);
        } catch (Exception e) {
            log.info(NO_SUBS_SIGNAL);
        }
    }


    @Subscribe(id = "intermediateSubscriptionsBtn", subject = "clickListener")
    public void onIntermediateSubscriptionsBtnClick(final ClickEvent<JmixButton> event) {
        String messageName = messageField.getValue();
        List<Execution> executions = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName(messageName)
                .list();
        log.info("Executions subscribed to message {}", messageName);
        executions.stream()
                .map(Execution::getId)
                .forEach(System.out::println);
    }

    @Subscribe(id = "throwMessageToExecutionBtn", subject = "clickListener")
    public void onThrowMessageToExecutionBtnClick(final ClickEvent<JmixButton> event) {
        String messageName = messageField.getValue();
        String executionId = executionField.getTypedValue();
        try {
            runtimeService.messageEventReceived(messageName, executionId);
        } catch (Exception e) {
            log.info(NO_EXEC_MESSAGE);
        }
    }

    @Subscribe(id = "startByKey", subject = "clickListener")
    public void onStartByKeyClick(final ClickEvent<JmixButton> event) {
        Optional<String> procDefId =
                Optional.ofNullable(procDefField.getTypedValue());
        runtimeService.startProcessInstanceByKey(procDefId.orElseThrow());
    }

    @Subscribe(id = "startById", subject = "clickListener")
    public void onStartByIdClick(final ClickEvent<JmixButton> event) {
        Optional<String> procDefId =
                Optional.ofNullable(procDefField.getTypedValue());
        runtimeService.startProcessInstanceById(procDefId.orElseThrow());

    }

    @Subscribe(id = "signalAsyncBtn", subject = "clickListener")
    public void onSignalAsyncBtnClick(final ClickEvent<JmixButton> event) {
        ProcessInstance throwingProcessInstance = runtimeService
                .startProcessInstanceByKey("throwing-process");
    }

    @Subscribe(id = "msgSbscrBtn", subject = "clickListener")
    public void onMsgSbscrBtnClick(final ClickEvent<JmixButton> event) {
        List<EventSubscription> messageSubscriptions = runtimeService.createEventSubscriptionQuery()
                .eventType("message")
                .list();
        System.out.println(messageSubscriptions);
    }

    @Subscribe(id = "signalSbscrBtn", subject = "clickListener")
    public void onSignalSbscrBtnClick(final ClickEvent<JmixButton> event) {
        List<EventSubscription> signalSubscriptions = runtimeService.createEventSubscriptionQuery()
                .eventType("signal")
                .list();
        System.out.println(signalSubscriptions);
    }

    @Subscribe(id = "timerSbscrBtn", subject = "clickListener")
    public void onTimerSbscrBtnClick(final ClickEvent<JmixButton> event) {


        List<EventSubscription> timerSubscriptions = runtimeService.createEventSubscriptionQuery()
                .eventType("timer")
                .list();
        List<Job> timerJobs = managementService.createTimerJobQuery().list();
        System.out.println(timerJobs);
    }

//    @Subscribe(id = "eventRegistryBtn", subject = "clickListener")
//    public void onEventRegistryBtnClick(final ClickEvent<JmixButton> event) {
//        eventRegistryService.sendTestMessage();
//        log.info("Message sent");
//    }

}