package com.example.shoppinglist.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestShopListRequest {
    private String shopListName;
    private List<RestProductInfo> products;
}
