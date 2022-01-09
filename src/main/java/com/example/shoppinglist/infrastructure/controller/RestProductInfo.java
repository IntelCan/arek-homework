package com.example.shoppinglist.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class RestProductInfo {
    @NotBlank
    private String name;
    @NotNull
    private float amount;
    @NotBlank
    private String unit;
}
