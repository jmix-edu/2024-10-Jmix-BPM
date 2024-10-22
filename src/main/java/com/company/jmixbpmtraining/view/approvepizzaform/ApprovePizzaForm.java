package com.company.jmixbpmtraining.view.approvepizzaform;


import com.company.jmixbpmtraining.entity.PizzaOrder;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.Outcome;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outcomes = {
        @Outcome(id = "approve"),
        @Outcome(id = "reject")
}, outputVariables = {
        @OutputVariable(name = "comment", type = String.class),
        @OutputVariable(name = "pizzaOrder", type = PizzaOrder.class)
})
@Route(value = "approve-pizza-form", layout = MainView.class)
@ViewController("jbt_ApprovePizzaForm")
@ViewDescriptor("approve-pizza-form.xml")
public class ApprovePizzaForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "comment")
    @ViewComponent
    private TypedTextField<String> commentField;

    @ProcessVariable(name = "pizzaOrder")
    private PizzaOrder pizzaOrder;

    @ViewComponent
    DataContext dataContext;
    @ViewComponent
    private InstanceContainer<PizzaOrder> pizzaOrderDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (pizzaOrder == null) {
            pizzaOrder = dataContext.create(PizzaOrder.class);
        }
        pizzaOrderDc.setItem(dataContext.merge(pizzaOrder));
    }

    @Subscribe(id = "approveBtn", subject = "clickListener")
    protected void onApproveBtnClick(ClickEvent<JmixButton> event) {
        dataContext.save();
        processFormContext.taskCompletion()
                .withOutcome("approve")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe(id = "rejectBtn", subject = "clickListener")
    protected void onRejectBtnClick(ClickEvent<JmixButton> event) {
        dataContext.save();
        processFormContext.taskCompletion()
                .withOutcome("reject")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}