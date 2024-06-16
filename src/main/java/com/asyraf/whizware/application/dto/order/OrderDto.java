package com.asyraf.whizware.application.dto.order;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class OrderDto {
    private UUID id;
    private UUID itemId;
    private UUID userId;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
