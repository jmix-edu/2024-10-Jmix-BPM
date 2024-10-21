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
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JmixEntity
@Table(name = "JBT_PIZZA_ITEM")
@Entity(name = "jbt_PizzaItem")
public class PizzaItem {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @InstanceName
    @Column(name = "NAME", nullable = false, length = 25)
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "STATUS", length = 25)
    private String status;

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public PizzaStatus getStatus() {
        return status == null ? null : PizzaStatus.fromId(status);
    }

    public void setStatus(PizzaStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}