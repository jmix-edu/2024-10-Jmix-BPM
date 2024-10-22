package com.company.jmixbpmtraining.view.startpizzaprocessform;


import com.company.jmixbpmtraining.entity.User;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outputVariables = {
        @OutputVariable(name = "message", type = String.class),
        @OutputVariable(name = "pizzaEater", type = User.class)
})
@Route(value = "start-pizza-process-form", layout = MainView.class)
@ViewController("jbt_StartPizzaProcessForm")
@ViewDescriptor("start-pizza-process-form.xml")
public class StartPizzaProcessForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "message")
    @ViewComponent
    private TypedTextField<String> messageField;
    @ProcessVariable(name = "pizzaEater")
    @ViewComponent
    private EntityComboBox<User> pizzaEaterField;

    @Subscribe(id = "startProcessBtn", subject = "clickListener")
    protected void onStartProcessBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.processStarting()
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }
}