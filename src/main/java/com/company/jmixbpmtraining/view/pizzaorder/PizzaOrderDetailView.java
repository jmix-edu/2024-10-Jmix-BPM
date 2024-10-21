package com.company.jmixbpmtraining.view.pizzaorder;

import com.company.jmixbpmtraining.entity.PizzaOrder;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "pizzaOrders/:id", layout = MainView.class)
@ViewController("jbt_PizzaOrder.detail")
@ViewDescriptor("pizza-order-detail-view.xml")
@EditedEntityContainer("pizzaOrderDc")
public class PizzaOrderDetailView extends StandardDetailView<PizzaOrder> {
}