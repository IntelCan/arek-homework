package com.example.shoppinglist.application;

import com.example.shoppinglist.application.command.AddProduct;
import com.example.shoppinglist.application.command.CheckProduct;
import com.example.shoppinglist.application.command.CreateShoppingList;
import com.example.shoppinglist.application.command.RemoveProduct;
import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingListFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceUnitTest {
    @Mock
    private ShoppingListRepository repository;

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Test
    void shouldNotCreateNewListAndThrowExceptionWhenListAlreadyExists() {
        //given
        String existingListName = "abc";
        when(repository.find(existingListName)).thenReturn(ShoppingListFactory.build(existingListName));

        //when
        assertThrows(ShoppingListAlreadyExists.class,
                () -> shoppingListService.create(new CreateShoppingList(existingListName, List.of())));
    }

    @Test
    void shouldNotAddNewProductWhenShoppingListNotExists() {
        //given
        String existingListName = "abc";
        when(repository.find(existingListName)).thenReturn(null);

        //when
        assertThrows(ShoppingListNotFoundException.class,
                () -> shoppingListService.add(new AddProduct(existingListName, mock(Product.class))));
    }

    @Test
    void shouldNotRemoveProductWhenShoppingListNotExists() {
        //given
        String existingListName = "abc";
        when(repository.find(existingListName)).thenReturn(null);

        //when
        assertThrows(ShoppingListNotFoundException.class,
                () -> shoppingListService.remove(new RemoveProduct(existingListName, "mockedProductName")));
    }

    @Test
    void markProductAsChecked() {
        //given
        String existingListName = "abc";
        when(repository.find(existingListName)).thenReturn(null);

        //when
        assertThrows(ShoppingListNotFoundException.class,
                () -> shoppingListService.markProductAsChecked(new CheckProduct(existingListName, "mockedProductName")));
    }
}