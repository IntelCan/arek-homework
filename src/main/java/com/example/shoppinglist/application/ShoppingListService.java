package com.example.shoppinglist.application;

import com.example.shoppinglist.application.command.AddProduct;
import com.example.shoppinglist.application.command.CheckProduct;
import com.example.shoppinglist.application.command.CreateShoppingList;
import com.example.shoppinglist.application.command.RemoveProduct;
import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingList;
import com.example.shoppinglist.domain.ShoppingListFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public void create(CreateShoppingList createShoppingList) {
        ShoppingList list = shoppingListRepository.find(createShoppingList.getShoppingListName());
        if (list != null) {
            throw new ShoppingListAlreadyExists();
        }

        ShoppingList shoppingList = ShoppingListFactory.build(createShoppingList.getShoppingListName());
        createShoppingList.getProducts().forEach(shoppingList::add);
        shoppingListRepository.save(shoppingList);
    }

    public void add(AddProduct addProduct) {
        ShoppingList shoppingList = findExistingShoppingList(addProduct.getShoppingListName());
        Product newProduct = shoppingList.add(addProduct.getProduct());
        shoppingListRepository.addProduct(shoppingList.getName(), newProduct);

    }

    public void remove(RemoveProduct removeProduct) {
        ShoppingList shoppingList = findExistingShoppingList(removeProduct.getShoppingListName());
        shoppingList.remove(removeProduct.getProductName());
        shoppingListRepository.removeProduct(shoppingList.getName(), removeProduct.getProductName());
    }

    public void markProductAsChecked(CheckProduct checkProduct) {
        ShoppingList shoppingList = findExistingShoppingList(checkProduct.getShoppingListName());
        Product checkedProduct = shoppingList.markAsChecked(checkProduct.getProductName());

        shoppingListRepository.updateProduct(shoppingList.getName(), checkedProduct);
    }

    private ShoppingList findExistingShoppingList(String shoppingListName) {
        ShoppingList shoppingList = shoppingListRepository.find(shoppingListName);
        if (shoppingList == null) {
            throw new ShoppingListNotFoundException();
        }
        return shoppingList;
    }

}
