/*
 * Copyright (c) 2023. Haulmont.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.company.jmixbpmtraining.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.List;

@JmixEntity
@Table(name = "JBT_PIZZA_ORDER", indexes = {
        @Index(name = "IDX_JBT_PIZZA_ORDER_INITIATOR", columnList = "INITIATOR_ID"),
        @Index(name = "IDX_JBT_PIZZA_ORDER_APPROVER", columnList = "APPROVER_ID")
})
@Entity(name = "jbt_PizzaOrder")
public class PizzaOrder {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @Column(name = "AMOUNT")
    private Double amount = 0.0;

    @JoinColumn(name = "INITIATOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User initiator;

    @JoinColumn(name = "APPROVER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User approver;

    @Column(name = "STATUS")
    private Integer status = OrderStatus.NEW.getId();

    @Column(name = "DELIVERY_NUMBER", length = 25)
    private String deliveryNumber;

    @Column(name = "PROCESS_INSTANCE_ID")
    private String processInstanceId;

    @Composition
    @OneToMany(mappedBy = "pizzaOrder")
    private List<OrderLine> orderLines;

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }
    
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    
    public OrderStatus getStatus() {
        return status == null ? null : OrderStatus.fromId(status);
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status == null ? null : status.getId();
    }
    
    public String getDeliveryNumber() {
        return deliveryNumber;
    }
    
    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }
    
    public User getApprover() {
        return approver;
    }
    
    public void setApprover(User approver) {
        this.approver = approver;
    }
    
    public User getInitiator() {
        return initiator;
    }
    
    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @InstanceName
    @DependsOnProperties({"id", "status"})
    public String getInstanceName() {
        return String.format("Pizza order - %s", id);
    }
}