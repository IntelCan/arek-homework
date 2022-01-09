package com.example.shoppinglist.application.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RemoveProduct {
    private final String shoppingListName;
    private final String productName;
}
