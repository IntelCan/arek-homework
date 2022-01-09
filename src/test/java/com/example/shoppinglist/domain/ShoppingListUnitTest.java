package com.example.shoppinglist.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListUnitTest {
    private static final String DEFAULT_SHOPPING_LIST_NAME = "test list";


    @Test
    void shouldAddProductToShoppingList() {
        //given
        ShoppingList shoppingList = new ShoppingList(DEFAULT_SHOPPING_LIST_NAME);
        Product potatoes = create3kgPotatoes();
        Product milk = create3BattlesOfMilk();

        //when
        shoppingList.add(potatoes);
        shoppingList.add(milk);

        //then
        assertEquals(shoppingList.getProducts().size(), 2);
    }

    @Test
    void shouldProtectFromDoubleAddingTheSameProduct() {
        //given
        ShoppingList shoppingList = new ShoppingList(DEFAULT_SHOPPING_LIST_NAME);
        Product potatoes = create3kgPotatoes();
        Product samePotatoes = create3kgPotatoes();

        //when
        shoppingList.add(potatoes);
        //and
        assertThrows(ProductAlreadyExistException.class, () -> shoppingList.add(samePotatoes));
    }

    @Test
    void shouldRemoveProductFromList() {
        //given
        ShoppingList shoppingList = new ShoppingList(DEFAULT_SHOPPING_LIST_NAME);
        Product potatoes = create3kgPotatoes();

        //when
        shoppingList.add(potatoes);
        //and
        shoppingList.remove(potatoes.getName());

        //then
        assertEquals(shoppingList.getProducts().size(), 0);
    }

    @Test
    void shouldCheckedProductFromList() {
        //given
        ShoppingList shoppingList = new ShoppingList(DEFAULT_SHOPPING_LIST_NAME);
        Product potatoes = create3kgPotatoes();
        shoppingList.add(potatoes);

        //when
        shoppingList.markAsChecked(potatoes.getName());

        //then
        assertTrue(shoppingList.getProducts().stream().findFirst().get().isChecked());
    }

    @Test
    void shouldThrowWhenThereIsNoProductForCheck() {
        //given
        ShoppingList shoppingList = new ShoppingList(DEFAULT_SHOPPING_LIST_NAME);
        Product potatoes = create3kgPotatoes();
        shoppingList.add(potatoes);

        //when
        assertThrows(ProductNotFoundException.class, () ->  shoppingList.markAsChecked("abc"));

    }


    private Product create3kgPotatoes() {
        return new Product("potatoes", 3, "kg");
    }

    private Product create3BattlesOfMilk() {
        return new Product("milk", 2, "battle");
    }

}