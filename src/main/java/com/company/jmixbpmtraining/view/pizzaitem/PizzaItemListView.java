package com.company.jmixbpmtraining.view.pizzaitem;

import com.company.jmixbpmtraining.entity.PizzaItem;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "pizzaItems", layout = MainView.class)
@ViewController("jbt_PizzaItem.list")
@ViewDescriptor("pizza-item-list-view.xml")
@LookupComponent("pizzaItemsDataGrid")
@DialogMode(width = "64em")
public class PizzaItemListView extends StandardListView<PizzaItem> {
}