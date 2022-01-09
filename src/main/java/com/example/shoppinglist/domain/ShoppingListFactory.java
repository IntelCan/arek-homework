package com.example.shoppinglist.domain;

import java.util.List;

public class ShoppingListFactory {

    public static ShoppingList build(String name){
        return new ShoppingList(name);
    }

    public static ShoppingList build(String name, List<Product> products) {
        return new ShoppingList(name, products);
    }
}
