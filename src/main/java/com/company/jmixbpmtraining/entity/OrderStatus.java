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

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum OrderStatus implements EnumClass<Integer> {

    NEW(40),
    IN_PROGRESS(50),
    APPROVED(10),
    REJECTED(20),
    CANCELLED(30);
    
    private final Integer id;
    
    OrderStatus(Integer id) {
        this.id = id;
    }
    
    @Override
    public Integer getId() {
        return id;
    }
    
    @Nullable
    public static OrderStatus fromId(Integer id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}