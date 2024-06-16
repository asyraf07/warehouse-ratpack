package com.asyraf.whizware.domain.item;

import com.asyraf.whizware.domain.BaseRepository;

import java.util.UUID;

public class ItemBaseRepository extends BaseRepository<Item, UUID> {
    public ItemBaseRepository() {
        super(Item.class);
    }
}
