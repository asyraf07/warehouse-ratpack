package com.asyraf.whizware.domain.order;

import com.asyraf.whizware.domain.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderBaseRepository extends BaseRepository<Order, UUID> {

    public OrderBaseRepository() {
        super(Order.class);
    }

    public List<Order> getByItemId(UUID itemId) {
        Map<String, Object> params = Map.of("itemId", itemId);
        return query("FROM Order o WHERE o.item.id = :itemId", params);
    }

    public List<Order> getByUserId(UUID userId) {
        Map<String, Object> params = Map.of("userId", userId);
        return query("FROM Order o WHERE o.user.id = :userId", params);
    }

}