package com.example.shoppinglist.infrastructure.controller;

import com.example.shoppinglist.application.ShoppingListAlreadyExists;
import com.example.shoppinglist.application.ShoppingListNotFoundException;
import com.example.shoppinglist.application.ShoppingListService;
import com.example.shoppinglist.application.command.AddProduct;
import com.example.shoppinglist.application.command.CheckProduct;
import com.example.shoppinglist.application.command.CreateShoppingList;
import com.example.shoppinglist.application.command.RemoveProduct;
import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ProductAlreadyExistException;
import com.example.shoppinglist.domain.ProductNotFoundException;
import com.example.shoppinglist.infrastructure.query.ShopListInfoDto;
import com.example.shoppinglist.infrastructure.query.ShopListQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/shopping-list")
@RequiredArgsConstructor
public class RestShoppingListController {
    private final ShoppingListService shoppingListService;
    private final ShopListQueryService shopListQueryService;

    @ApiOperation(value = "Create shopping list")
    @PostMapping
    public void create(@RequestBody RestShopListRequest request) {
        List<Product> products = request.getProducts().stream()
                .map(ProductConverter::from)
                .collect(Collectors.toList());
        shoppingListService.create(new CreateShoppingList(request.getShopListName(), products));
    }

    @ApiOperation(value = "Add product for existing shopping list")
    @PostMapping("/{shoppingListName}/product")
    public void addProduct(@PathVariable String shoppingListName,
                           @RequestBody RestProductInfo restProductInfo) {
        shoppingListService.add(new AddProduct(shoppingListName, ProductConverter.from(restProductInfo)));
    }

    @ApiOperation(value = "Mark as checked product from shopping list")
    @PatchMapping("/{shoppingListName}/{productName}")
    public void markAsChecked(@PathVariable String shoppingListName,
                              @PathVariable String productName) {
        shoppingListService.markProductAsChecked(new CheckProduct(shoppingListName, productName));
    }

    @ApiOperation(value = "Remove product from shopping list")
    @DeleteMapping("/{shoppingListName}/{productName}")
    public void removeProduct(@PathVariable String shoppingListName,
                              @PathVariable String productName) {
        shoppingListService.remove(new RemoveProduct(shoppingListName, productName));
    }

    @ApiOperation(value = "Get shopping list details")
    @GetMapping("/{name}")
    public ShopListInfoDto get(@PathVariable String name) {
        return shopListQueryService.get(name);
    }


    @ExceptionHandler(ShoppingListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundShoppingListException(ShoppingListNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Given shopping list not found");
    }

    @ExceptionHandler(ShoppingListAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleListAlreadyExistsException(ShoppingListAlreadyExists exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Shopping list already exists");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoFoundProductException(ProductNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Given product not found");
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Product already exists");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleAnyUncaughtException(Throwable exception) {
        log.error("Internal error occurred", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }

}
