package com.company.jmixbpmtraining.entity;

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum PizzaStatus implements EnumClass<String> {

    NEW("N"),
    IN_USE("U"),
    OBSOLETE("O");

    private final String id;

    PizzaStatus(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Nullable
    public static PizzaStatus fromId(String id) {
        for (PizzaStatus at : PizzaStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}