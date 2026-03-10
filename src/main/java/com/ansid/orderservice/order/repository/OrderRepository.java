package com.ansid.orderservice.order.repository;

import com.ansid.orderservice.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("""
        select distinct o
        from OrderEntity o
        left join fetch o.items
        where o.id = :id
       """)
    Optional<OrderEntity> findByIdWithItems(UUID id);

    @EntityGraph(attributePaths = "items")
    Page<OrderEntity> findAll(Pageable pageable);
}
