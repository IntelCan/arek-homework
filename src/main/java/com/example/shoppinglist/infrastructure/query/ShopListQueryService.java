package com.example.shoppinglist.infrastructure.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopListQueryService {
    private final ShopListQueryRepository shopListQueryRepository;

    public ShopListInfoDto get(String shopListName) {
        return shopListQueryRepository.findByName(shopListName);
    }
}
