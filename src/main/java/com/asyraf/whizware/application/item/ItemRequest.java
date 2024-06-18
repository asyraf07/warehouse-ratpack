package com.asyraf.whizware.application.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest {
    private String name;
    private BigDecimal price;
}
