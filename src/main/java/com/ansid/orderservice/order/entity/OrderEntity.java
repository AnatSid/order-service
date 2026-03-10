package com.ansid.orderservice.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "customer_id", nullable = false)
    UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    OrderStatus status;

    @Column(name = "total_amount", nullable = false)
    BigDecimal totalAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    OffsetDateTime createdAt;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @BatchSize(size = 20)
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity(UUID customerId, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
        this.createdAt = OffsetDateTime.now();
    }

    public void markAsPaid() {
        if (this.status != OrderStatus.NEW) {
            throw new IllegalStateException("Only NEW orders can be paid");
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (this.status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid order cannot be cancelled");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void addItem(String productName, int quantity) {
        OrderItemEntity item = new OrderItemEntity(productName, quantity, this);
        items.add(item);
    }

}
