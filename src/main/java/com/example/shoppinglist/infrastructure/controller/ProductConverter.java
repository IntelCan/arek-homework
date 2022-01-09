
package com.example.shoppinglist.infrastructure.controller;


import com.example.shoppinglist.domain.Product;

class ProductConverter {

    static Product from(RestProductInfo productInfo) {
        return Product.builder()
                .name(productInfo.getName())
                .amount(productInfo.getAmount())
                .unit(productInfo.getUnit())
                .build();
    }
}