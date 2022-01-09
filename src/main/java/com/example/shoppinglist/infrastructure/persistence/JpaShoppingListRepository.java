package com.example.shoppinglist.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {
    Optional<ShoppingListEntity> findByName(String name);
}