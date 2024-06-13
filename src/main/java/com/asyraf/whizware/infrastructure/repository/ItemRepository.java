package com.asyraf.whizware.infrastructure.repository;

import com.asyraf.whizware.domain.item.Item;

import java.util.UUID;

public class ItemRepository extends Repository<Item, UUID> {
    public ItemRepository() {
        super(Item.class);
    }
}
