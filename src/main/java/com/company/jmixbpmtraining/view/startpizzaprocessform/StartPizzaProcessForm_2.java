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

package com.company.jmixbpmtraining.view.startpizzaprocessform;


import com.company.jmixbpmtraining.entity.User;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.listbox.JmixMultiSelectListBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(
        allowedProcessKeys = {"pizzaProcess"}
)
@Route(value = "StartPizzaProcessForm_2", layout = MainView.class)
@ViewController("jbt_StartPizzaProcessForm2")
@ViewDescriptor("start-pizza-process-form-2.xml")
@DialogMode(width = "25em", height = "AUTO")
public class StartPizzaProcessForm_2 extends StandardView {
    
    @Autowired
    private ProcessFormContext processFormContext;
    
    @ProcessVariable
    @ViewComponent
    private TypedTextField<String> message;
    
    @ProcessVariable
    @ViewComponent
    private JmixMultiSelectListBox<User> pizzaEaters;

    @Subscribe(id = "startProcessBtn", subject = "clickListener")
    protected void onStartProcessBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.processStarting()
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }
}