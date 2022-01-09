package com.example.shoppinglist.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestShopListRequest {
    @NotBlank
    private String shopListName;
    @Valid
    private List<RestProductInfo> products;
}
