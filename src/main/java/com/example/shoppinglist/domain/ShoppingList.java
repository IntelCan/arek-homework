package com.example.shoppinglist.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ShoppingList {
    private final String name;
    private final List<Product> products;

    ShoppingList(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public ShoppingList(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Product add(Product product) {
        if (exists(product)) {
            throw new ProductAlreadyExistException("Product with the same name exists");
        }

        products.add(product);

        return product;
    }

    public void remove(String productName) {
        products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .map(products::remove);
    }

    public Product markAsChecked(String productName) {
        return products.stream()
                .filter(product -> product.getName().equals(productName))
                .findAny()
                .map(Product::markAsChecked)
                .orElseThrow(ProductNotFoundException::new);
    }

    private boolean exists(Product product) {
        return products.stream().anyMatch(p -> p.getName().equals(product.getName()));
    }
}
