package com.example.shoppinglist.application.command;

import com.example.shoppinglist.domain.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CreateShoppingList {
    private final String shoppingListName;
    private final List<Product> products;
}
