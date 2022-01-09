package com.example.shoppinglist.infrastructure.persistence;

import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.infrastructure.query.ProductInfoDto;

import java.util.UUID;

class ProductConverter {
    static Product from(ProductEntity productEntity) {
        return Product.builder()
                .name(productEntity.getName())
                .amount(productEntity.getAmount())
                .unit(productEntity.getUnit())
                .checked(productEntity.getChecked())
                .build();
    }

    static ProductEntity from(Product product) {
        return ProductEntity.builder()
                .id(UUID.randomUUID())
                .name(product.getName())
                .amount(product.getAmount())
                .unit(product.getUnit())
                .checked(product.isChecked())
                .build();
    }

    static ProductInfoDto toReadModel(ProductEntity productEntity) {
        return ProductInfoDto.builder()
                .name(productEntity.getName())
                .amount(productEntity.getAmount())
                .unit(productEntity.getUnit())
                .checked(productEntity.getChecked())
                .build();
    }

}
