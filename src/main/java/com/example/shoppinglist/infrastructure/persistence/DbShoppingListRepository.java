package com.example.shoppinglist.infrastructure.persistence;

import com.example.shoppinglist.application.ShoppingListRepository;
import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class DbShoppingListRepository implements ShoppingListRepository {
    private final JpaShoppingListRepository shoppingListRepository;
    private final JpaProductRepository productRepository;

    @Override
    public ShoppingList find(String name) {
        return shoppingListRepository.findByName(name)
                .map(ShoppingListConverter::from)
                .orElse(null);
    }

    @Override
    public ShoppingList save(ShoppingList shoppingList) {
        ShoppingListEntity savedShoppingList = shoppingListRepository.save(ShoppingListConverter.from(shoppingList));
        shoppingList.getProducts().stream()
                .map(ProductConverter::from)
                .forEach(savedShoppingList::addProduct);
        shoppingListRepository.save(savedShoppingList);
        return ShoppingListConverter.from(savedShoppingList);
    }

    @Override
    public ShoppingList addProduct(String shopListName, Product product) {
        return shoppingListRepository.findByName(shopListName)
                .map(entity -> {
                    entity.addProduct(ProductConverter.from(product));
                    return entity;
                }).map(ShoppingListConverter::from)
                .orElse(null);
    }

    @Override
    public void updateProduct(String shopListName, Product product) {
        productRepository.findByNameAndShoppingList_Name(product.getName(), shopListName)
                .map(productEntity -> updateProduct(product, productEntity))
                .ifPresent(productRepository::save);
    }

    @Override
    public void removeProduct(String shopListName, String productName) {
        shoppingListRepository.findByName(shopListName)
                .ifPresent(shoppingListEntity -> remove(shoppingListEntity, productName));
    }

    private void remove(ShoppingListEntity shoppingListEntity, String productName) {
        shoppingListEntity.getProducts().stream()
                                        .filter(productEntity -> productEntity.getName().equals(productName))
                                        .forEach(shoppingListEntity::removeProduct);
        shoppingListRepository.save(shoppingListEntity);
    }

    private ProductEntity updateProduct(Product product, ProductEntity productEntity) {
        productEntity.setChecked(product.isChecked());
        return productEntity;
    }
}