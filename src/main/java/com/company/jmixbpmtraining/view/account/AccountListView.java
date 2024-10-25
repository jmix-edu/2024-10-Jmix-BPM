package com.company.jmixbpmtraining.view.account;

import com.company.jmixbpmtraining.entity.Account;
import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "accounts", layout = MainView.class)
@ViewController("jbt_Account.list")
@ViewDescriptor("account-list-view.xml")
@LookupComponent("accountsDataGrid")
@DialogMode(width = "64em")
public class AccountListView extends StandardListView<Account> {
}