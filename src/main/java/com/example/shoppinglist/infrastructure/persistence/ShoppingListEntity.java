package com.example.shoppinglist.infrastructure.persistence;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "shopping_lists")
class ShoppingListEntity implements Persistable<UUID> {

    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 256)
    private Set<ProductEntity> products = new HashSet<>();

    @Transient
    protected boolean newOne;

    @Override
    public boolean isNew() {
        return newOne;
    }

    void addProduct(ProductEntity productEntity) {
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(productEntity);
        productEntity.setShoppingList(this);
    }

    void removeProduct(ProductEntity productEntity) {
        products.remove(productEntity);
        productEntity.setShoppingList(null);
    }

}