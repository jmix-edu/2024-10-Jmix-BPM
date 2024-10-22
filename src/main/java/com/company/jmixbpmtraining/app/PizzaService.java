package com.company.jmixbpmtraining.app;

import com.company.jmixbpmtraining.entity.OrderLine;
import com.company.jmixbpmtraining.entity.PizzaOrder;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.SystemAuthenticator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("jbt_PizzaService")
public class PizzaService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PizzaService.class);

    @Autowired
    private DataManager dataManager;

    @Autowired
    private SystemAuthenticator authenticator;

    public double calculateAmount(PizzaOrder order) {
        double amount = 0;
        List<OrderLine> orderLines = getOrderLines(order);
        for(OrderLine orderLine : orderLines) {
            double linePrice = orderLine.getQuantity() * orderLine.getPizzaItem().getPrice();
            amount = amount + linePrice;
        }

        authenticator.begin();
        try {
            order.setAmount(amount);
            dataManager.save(order);
        } finally {
            authenticator.end();
        }

        log.info("Order amount {}", amount);
        return amount;
    }

    private List<OrderLine> getOrderLines(PizzaOrder pizzaOrder) {
        authenticator.begin();
        try {
            return dataManager.load(OrderLine.class)
                    .condition(PropertyCondition.equal("pizzaOrder", pizzaOrder))
                    .list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            authenticator.end();
        }
    }
}