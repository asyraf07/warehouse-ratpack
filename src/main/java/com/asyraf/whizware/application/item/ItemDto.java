package com.asyraf.whizware.application.item;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ItemDto {
    private UUID id;
    private String name;
    private BigDecimal price;
}
