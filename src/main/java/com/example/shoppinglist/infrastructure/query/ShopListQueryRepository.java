package com.example.shoppinglist.infrastructure.query;

public interface ShopListQueryRepository {
    ShopListInfoDto findByName(String name);
}
