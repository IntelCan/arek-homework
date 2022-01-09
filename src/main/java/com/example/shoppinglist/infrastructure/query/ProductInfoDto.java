package com.example.shoppinglist.infrastructure.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfoDto {
    private final String name;
    private final float amount;
    private final String unit;
    private final boolean checked;
}
