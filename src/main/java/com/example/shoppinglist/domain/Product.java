package com.example.shoppinglist.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private final String name;
    private float amount;
    private String unit;
    private boolean checked;

    public Product(String name, float amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    @Builder
    public Product(String name, float amount, String unit, boolean checked) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.checked = checked;
    }

    Product markAsChecked() {
        checked = true;
        return this;
    }
}
