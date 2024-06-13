package com.asyraf.whizware.domain.order;

import com.asyraf.whizware.application.dto.order.OrderDto;
import com.asyraf.whizware.domain.item.Item;
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
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Item item;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public OrderDto toDto() {
        return OrderDto.builder()
            .id(this.id)
            .itemId(this.item.getId())
            .quantity(this.quantity)
            .price(this.price)
            .totalPrice(this.totalPrice)
            .build();
    }

}
