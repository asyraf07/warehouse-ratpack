package com.asyraf.whizware.infrastructure.repository;

import com.asyraf.whizware.domain.order.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderRepository extends Repository<Order, UUID> {

    public OrderRepository() {
        super(Order.class);
    }

    public List<Order> getByItemId(UUID itemId) {
        Map<String, Object> params = Map.of("itemId", itemId);
        return query("FROM Order o WHERE o.item.id = :itemId", params);
    }

}