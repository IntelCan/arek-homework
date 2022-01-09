package com.example.shoppinglist.infrastructure.persistence;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
class ProductEntity implements Persistable<UUID> {
    @Id
    private UUID id;

    private String name;

    private float amount;

    private String unit;

    @Column(name = "is_checked", columnDefinition = "boolean default false", nullable = false)
    private Boolean checked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingListEntity shoppingList;

    @Transient
    protected boolean newOne;

    @Override
    public boolean isNew() {
        return newOne;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity )) return false;
        return id != null && id.equals(((ProductEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}