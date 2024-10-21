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

import io.jmix.core.DeletePolicy;
import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@JmixEntity
@Table(name = "JBT_ORDER_LINE", indexes = {
        @Index(name = "IDX_JBT_ORDER_LINE_PIZZA_EATER", columnList = "PIZZA_EATER_ID"),
        @Index(name = "IDX_JBT_ORDER_LINE_PIZZA_ORDER", columnList = "PIZZA_ORDER_ID")
})
@Entity(name = "jbt_OrderLine")
public class OrderLine {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;
    
    @JoinColumn(name = "PIZZA_ITEM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private PizzaItem pizzaItem;
    
    @Column(name = "QUANTITY")
    private Long quantity;
    
    @Column(name = "SPECIAL_REQUIREMENTS")
    private String specialRequirements;
    
    @JoinColumn(name = "PIZZA_EATER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User pizzaEater;
    
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PIZZA_ORDER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PizzaOrder pizzaOrder;
    
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;
    
    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    
    public Long getQuantity() {
        return quantity;
    }
    
    public PizzaOrder getPizzaOrder() {
        return pizzaOrder;
    }
    
    public void setPizzaOrder(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }
    
    public User getPizzaEater() {
        return pizzaEater;
    }
    
    public void setPizzaEater(User pizzaEater) {
        this.pizzaEater = pizzaEater;
    }
    
    public String getSpecialRequirements() {
        return specialRequirements;
    }
    
    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }
    
    public PizzaItem getPizzaItem() {
        return pizzaItem;
    }
    
    public void setPizzaItem(PizzaItem pizzaItem) {
        this.pizzaItem = pizzaItem;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    @InstanceName
    @DependsOnProperties({"id", "pizzaItem", "pizzaEater"})
    public String getInstanceName(MetadataTools metadataTools, DatatypeFormatter datatypeFormatter) {
        return String.format("%s %s %s",
                datatypeFormatter.formatInteger(id),
                metadataTools.format(pizzaItem),
                metadataTools.format(pizzaEater));
    }
}