package com.asyraf.whizware.domain.order;

import com.asyraf.whizware.application.response.ListResponse;
import com.asyraf.whizware.application.response.Response;
import com.asyraf.whizware.application.dto.order.OrderRequest;
import com.asyraf.whizware.application.dto.order.OrderDto;
import com.asyraf.whizware.exception.BadRequestException;
import com.asyraf.whizware.domain.item.Item;
import com.asyraf.whizware.infrastructure.repository.ItemRepository;
import com.asyraf.whizware.infrastructure.repository.OrderRepository;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    ItemRepository itemRepository;

    public ListResponse<OrderDto> getAllOrder() {
        return ListResponse.<OrderDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(orderRepository.getAll().stream().map(Order::toDto).collect(Collectors.toList()))
            .build();
    }

    public Response<OrderDto> getOrder(UUID id) throws BadRequestException {
        return Response.<OrderDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(orderRepository.get(id).orElseThrow(() -> new BadRequestException("Not found!")).toDto())
            .build();
    }

    public Response<OrderDto> addOrder(OrderRequest request) throws Exception {
        Item item = itemRepository.get(request.getItemId()).orElseThrow(() -> new BadRequestException("Item not found!"));
        return Response.<OrderDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(orderRepository.save(Order.builder()
                .item(item)
                .quantity(request.getQuantity())
                .price(item.getPrice())
                .totalPrice(item.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
                .build()).toDto())
            .build();
    }

    public Response<OrderDto> updateOrder(UUID id, OrderRequest request) throws Exception {
        Order order = orderRepository.get(id).orElseThrow(() -> new BadRequestException("Order not found!"));
        Item item = itemRepository.get(request.getItemId()).orElseThrow(() -> new BadRequestException("Item not found!"));
        order.setItem(item);
        order.setQuantity(request.getQuantity());
        order.setPrice(item.getPrice());
        order.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        return Response.<OrderDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(orderRepository.save(order).toDto())
            .build();
    }

    public void deleteOrder(UUID id) throws Exception {
        Order order = orderRepository.get(id).orElseThrow(() -> new BadRequestException("Order not found!"));
        orderRepository.delete(order);
    }

}
