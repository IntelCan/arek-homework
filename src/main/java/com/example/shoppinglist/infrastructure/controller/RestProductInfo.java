package com.example.shoppinglist.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestProductInfo {
    private String name;
    private float amount;
    private String unit;
}
