package com.example.shoppinglist.infrastructure.controller;

import com.example.shoppinglist.application.ShoppingListService;
import com.example.shoppinglist.application.command.CreateShoppingList;
import com.example.shoppinglist.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/shopping-list")
@RequiredArgsConstructor
public class RestShopListController {
    private final ShoppingListService shoppingListService;

    @PostMapping
    public void create(@RequestBody RestShopListRequest request) {
        List<Product> products = request.getProducts().stream()
                                                      .map(ProductConverter::from)
                                                      .collect(Collectors.toList());
        shoppingListService.create(new CreateShoppingList(request.getShopListName(), products));
    }

}
