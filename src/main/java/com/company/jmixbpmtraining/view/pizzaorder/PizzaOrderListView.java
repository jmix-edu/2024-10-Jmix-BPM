package com.company.jmixbpmtraining.view.pizzaorder;

import com.company.jmixbpmtraining.entity.PizzaOrder;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "pizzaOrders", layout = MainView.class)
@ViewController("jbt_PizzaOrder.list")
@ViewDescriptor("pizza-order-list-view.xml")
@LookupComponent("pizzaOrdersDataGrid")
@DialogMode(width = "64em")
public class PizzaOrderListView extends StandardListView<PizzaOrder> {
}