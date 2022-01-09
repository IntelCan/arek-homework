package com.example.shoppinglist.infrastructure.query;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShopListInfoDto {
    private final String name;
    private final List<ProductInfoDto> products;
}
