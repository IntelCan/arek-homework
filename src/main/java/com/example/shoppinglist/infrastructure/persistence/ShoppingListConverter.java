package com.example.shoppinglist.infrastructure.persistence;

import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingList;
import com.example.shoppinglist.domain.ShoppingListFactory;
import com.example.shoppinglist.infrastructure.query.ProductInfoDto;
import com.example.shoppinglist.infrastructure.query.ShopListInfoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class ShoppingListConverter {

    static ShoppingList from(ShoppingListEntity shoppingListEntity) {
        List<Product> products = new ArrayList<>();
        if (shoppingListEntity.getProducts() != null) {
             products = shoppingListEntity.getProducts().stream()
                    .map(ProductConverter::from)
                    .collect(Collectors.toList());
        }
        return ShoppingListFactory.build(shoppingListEntity.getName(), products);
    }

    static ShoppingListEntity from(ShoppingList shoppingList) {
        return ShoppingListEntity.builder()
                .id(UUID.randomUUID())
                .name(shoppingList.getName())
                .build();
    }

    static ShopListInfoDto toReadModel(ShoppingListEntity shoppingListEntity) {
        List<ProductInfoDto> products = shoppingListEntity.getProducts().stream()
                .map(ProductConverter::toReadModel)
                .collect(Collectors.toList());

       return ShopListInfoDto.builder()
                .name(shoppingListEntity.getName())
                .products(products)
                .build();
    }
}
