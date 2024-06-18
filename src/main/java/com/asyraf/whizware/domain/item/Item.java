package com.asyraf.whizware.domain.item;

import com.asyraf.whizware.application.item.ItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal price;

    public ItemDto toDto() {
        return ItemDto.builder()
            .id(this.id)
            .name(this.name)
            .price(this.price)
            .build();
    }
}
