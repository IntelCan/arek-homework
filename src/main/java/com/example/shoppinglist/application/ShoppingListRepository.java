package com.example.shoppinglist.application;

import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingList;

public interface ShoppingListRepository {
    ShoppingList find(String name);

    ShoppingList save(ShoppingList shoppingList);

    ShoppingList addProduct(String shopListName, Product product);

    void updateProduct(String shopListName, Product product);

    void removeProduct(String shopListName, String productName);

}
