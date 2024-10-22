package com.company.jmixbpmtraining.view.choosepizzaform;


import com.company.jmixbpmtraining.entity.PizzaItem;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.Outcome;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outcomes = {
        @Outcome(id = "pizza_chosen"),
        @Outcome(id = "not_hungry")
}, outputVariables = {
        @OutputVariable(name = "message", type = String.class),
        @OutputVariable(name = "pizzaItem", type = PizzaItem.class),
        @OutputVariable(name = "specialRequirements", type = JmixTextArea.class)
})
@Route(value = "choose-pizza-form", layout = MainView.class)
@ViewController("jbt_ChoosePizzaForm")
@ViewDescriptor("choose-pizza-form.xml")
public class ChoosePizzaForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "message")
    @ViewComponent
    private TypedTextField<String> messageField;
    @ProcessVariable(name = "pizzaItem")
    @ViewComponent
    private EntityComboBox<PizzaItem> pizzaItemField;
    @ProcessVariable(name = "specialRequirements")
    @ViewComponent
    private JmixTextArea specialRequirementsField;

    @Subscribe(id = "pizza_chosenBtn", subject = "clickListener")
    protected void onPizza_chosenBtnClick(ClickEvent<JmixButton> event) {
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