package com.example.shoppinglist.infrastructure.persistence;

import com.example.shoppinglist.application.ShoppingListRepository;
import com.example.shoppinglist.domain.Product;
import com.example.shoppinglist.domain.ShoppingList;
import com.example.shoppinglist.infrastructure.query.ShopListInfoDto;
import com.example.shoppinglist.infrastructure.query.ShopListQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class DbShoppingListRepository implements ShoppingListRepository, ShopListQueryRepository {
    private final JpaShoppingListRepository shoppingListRepository;
    private final JpaProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ShoppingList find(String name) {
        return shoppingListRepository.findByName(name)
                .map(ShoppingListConverter::from)
                .orElse(null);
    }

    @Override
    public ShoppingList save(ShoppingList shoppingList) {
        ShoppingListEntity listToSave = ShoppingListConverter.from(shoppingList);
        listToSave.setNewOne(true);
        ShoppingListEntity savedShoppingList = shoppingListRepository.save(listToSave);
        shoppingList.getProducts().stream()
                .map(ProductConverter::from)
                .forEach(savedShoppingList::addProduct);
        shoppingListRepository.save(savedShoppingList);
        return ShoppingListConverter.from(savedShoppingList);
    }

    @Override
    @Transactional
    public ShoppingList addProduct(String shopListName, Product product) {
        return shoppingListRepository.findByName(shopListName)
                .map(entity -> {
                    entity.addProduct(ProductConverter.from(product));
                    entity.setNewOne(true);
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
    @Transactional
    public void removeProduct(String shopListName, String productName) {
        shoppingListRepository.findByName(shopListName)
                .ifPresent(shoppingListEntity -> remove(shoppingListEntity, productName));
    }

    @Override
    @Transactional(readOnly = true)
    public ShopListInfoDto findByName(String name) {
        return shoppingListRepository.findByName(name)
                .map(ShoppingListConverter::toReadModel)
                .orElse(null);
    }

    private void remove(ShoppingListEntity shoppingListEntity, String productName) {
        Optional<ProductEntity> product = shoppingListEntity.getProducts().stream()
                .filter(productEntity -> productEntity.getName().equals(productName))
                .findFirst();

        if (product.isPresent()) {
            shoppingListEntity.removeProduct(product.get());
            shoppingListRepository.save(shoppingListEntity);
        }

    }

    private ProductEntity updateProduct(Product product, ProductEntity productEntity) {
        productEntity.setChecked(product.isChecked());
        return productEntity;
    }
}