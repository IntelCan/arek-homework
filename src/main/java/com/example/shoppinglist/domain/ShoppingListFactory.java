package com.example.shoppinglist.domain;

public class ShoppingListFactory {

    public static ShoppingList build(String name){
        return new ShoppingList(name);
    }

}
