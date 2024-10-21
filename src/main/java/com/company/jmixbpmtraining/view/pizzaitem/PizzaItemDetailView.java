package com.company.jmixbpmtraining.view.pizzaitem;

import com.company.jmixbpmtraining.entity.PizzaItem;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "pizzaItems/:id", layout = MainView.class)
@ViewController("jbt_PizzaItem.detail")
@ViewDescriptor("pizza-item-detail-view.xml")
@EditedEntityContainer("pizzaItemDc")
public class PizzaItemDetailView extends StandardDetailView<PizzaItem> {
}