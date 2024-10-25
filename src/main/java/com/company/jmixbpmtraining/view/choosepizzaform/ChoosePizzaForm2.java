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

package com.company.jmixbpmtraining.view.choosepizzaform;


import com.company.jmixbpmtraining.entity.PizzaItem;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.Outcome;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(
//        allowedProcessKeys = {"pizzaProcess"},
        outcomes = {
            @Outcome(id = "pizza_chosen"),
            @Outcome(id = "not_hungry")
        },
        outputVariables = {
                @OutputVariable(name = "message", type = String.class),
                @OutputVariable(name = "pizzaItem", type = PizzaItem.class),
                @OutputVariable(name = "quantity", type = Long.class),
                @OutputVariable(name = "specialRequirements", type = String.class)
        }
)
@Route(value = "ChoosePizzaForm2", layout = MainView.class)
@ViewController("jbt_ChoosePizzaForm2")
@ViewDescriptor("choose-pizza-form-2.xml")
@DialogMode(width = "25em", height = "AUTO")
public class ChoosePizzaForm2 extends StandardView {
    
    @Autowired
    private ProcessFormContext processFormContext;
    
    @ProcessVariable
    @ViewComponent
    private TypedTextField<String> message;
    
    @ViewComponent
    private ListBox<PizzaItem> pizzaItemListBox;
    
    @ProcessVariable
    private PizzaItem pizzaItem;
    
    @ProcessVariable
    @ViewComponent
    private JmixTextArea specialRequirements;
   
    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        pizzaItemListBox.setValue(pizzaItem);
    }
    
    @Subscribe(id = "pizza_chosenBtn", subject = "clickListener")
    protected void onPizza_chosenBtnClick(ClickEvent<JmixButton> event) {
        pizzaItem = pizzaItemListBox.getValue();
        processFormContext.taskCompletion()
                .withOutcome("pizza_chosen")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
    
    @Subscribe(id = "not_hungryBtn", subject = "clickListener")
    protected void onNot_hungryBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.taskCompletion()
                .withOutcome("not_hungry")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}