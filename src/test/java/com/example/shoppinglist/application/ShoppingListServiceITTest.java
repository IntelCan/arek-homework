package com.example.shoppinglist.application;

import com.example.shoppinglist.application.command.AddProduct;
import com.example.shoppinglist.application.command.CheckProduct;
import com.example.shoppinglist.application.command.CreateShoppingList;
import com.example.shoppinglist.application.command.RemoveProduct;
import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingListServiceITTest {
    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Test
    void shouldCreateShoppingList() {
        //given
        String shoppingListName = "friday shopping";
        Product firstProduct =  new Product("potatoes", 3, "kg");
        Product secondProduct =  new Product("milk", 4, "l");

        CreateShoppingList shoppingList = new CreateShoppingList(shoppingListName, List.of(firstProduct, secondProduct));

        //when
        shoppingListService.create(shoppingList);

        //then
        ShoppingList savedList = shoppingListRepository.find(shoppingListName);
        assertNotNull(savedList);
        assertEquals(2, savedList.getProducts().size());
    }

    @Test
    void shouldAddProductToExistingShoppingList() {
        //given
        String shoppingListName = "friday shopping";
        Product firstProduct =  new Product("potatoes", 3, "kg");
        Product secondProduct =  new Product("milk", 4, "l");

        CreateShoppingList shoppingList = new CreateShoppingList(shoppingListName, List.of(firstProduct, secondProduct));
        shoppingListService.create(shoppingList);

        Product thirdProduct =  new Product("sponges", 1, "sz");

        //when
        shoppingListService.add(new AddProduct(shoppingListName, thirdProduct));

        //then
        ShoppingList savedList = shoppingListRepository.find(shoppingListName);
        assertNotNull(savedList);
        assertEquals(3, savedList.getProducts().size());
    }

    @Test
    void shouldRemoveProductFromExistingShoppingList() {
        //given
        String shoppingListName = "friday shopping";
        Product firstProduct =  new Product("potatoes", 3, "kg");
        Product secondProduct =  new Product("milk", 4, "l");

        CreateShoppingList shoppingList = new CreateShoppingList(shoppingListName, List.of(firstProduct, secondProduct));
        shoppingListService.create(shoppingList);

        //when
        shoppingListService.remove(new RemoveProduct(shoppingListName, "milk"));

        //then
        ShoppingList savedList = shoppingListRepository.find(shoppingListName);
        assertNotNull(savedList);
        assertEquals(1, savedList.getProducts().size());
    }

    @Test
    void shouldMarkProductAsCheckedOnExistingShoppingList() {
        //given
        String shoppingListName = "friday shopping";
        Product firstProduct =  new Product("potatoes", 3, "kg");
        Product secondProduct =  new Product("milk", 4, "l");

        CreateShoppingList shoppingList = new CreateShoppingList(shoppingListName, List.of(firstProduct, secondProduct));
        shoppingListService.create(shoppingList);

        //when
        shoppingListService.markProductAsChecked(new CheckProduct(shoppingListName, "milk"));

        //then
        ShoppingList savedList = shoppingListRepository.find(shoppingListName);
        assertNotNull(savedList);
        Product modifiedProduct = savedList.getProducts().stream()
                                                         .filter(product -> product.getName().equals("milk"))
                                                         .findAny().orElse(null);
        assertNotNull(modifiedProduct);
        assertTrue(modifiedProduct.isChecked());
    }





}
