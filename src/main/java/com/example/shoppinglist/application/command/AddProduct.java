package com.example.shoppinglist.application.command;

import com.example.shoppinglist.domain.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddProduct {
    private final String shoppingListName;
    private final Product product;
}
