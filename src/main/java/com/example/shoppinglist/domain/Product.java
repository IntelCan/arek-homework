package com.example.shoppinglist.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private final String name;
    private float amount;
    private String unit;
    private boolean checked;

    @Builder
    public Product(String name, float amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    Product markAsChecked() {
        checked = true;
        return this;
    }
}
