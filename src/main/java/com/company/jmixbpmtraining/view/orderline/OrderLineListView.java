package com.company.jmixbpmtraining.view.orderline;

import com.company.jmixbpmtraining.entity.OrderLine;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "orderLines", layout = MainView.class)
@ViewController("jbt_OrderLine.list")
@ViewDescriptor("order-line-list-view.xml")
@LookupComponent("orderLinesDataGrid")
@DialogMode(width = "64em")
public class OrderLineListView extends StandardListView<OrderLine> {
}