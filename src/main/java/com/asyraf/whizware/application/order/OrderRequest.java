package com.asyraf.whizware.application.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
    private UUID itemId;
    private UUID userId;
    private Long quantity;
}
