package com.company.jmixbpmtraining.view.orderline;

import com.company.jmixbpmtraining.entity.OrderLine;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "orderLines/:id", layout = MainView.class)
@ViewController("jbt_OrderLine.detail")
@ViewDescriptor("order-line-detail-view.xml")
@EditedEntityContainer("orderLineDc")
public class OrderLineDetailView extends StandardDetailView<OrderLine> {
}