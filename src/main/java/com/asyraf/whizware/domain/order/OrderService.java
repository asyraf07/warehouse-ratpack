package com.asyraf.whizware.domain.order;

import com.asyraf.whizware.application.response.ListResponse;
import com.asyraf.whizware.application.response.Response;
import com.asyraf.whizware.application.dto.order.OrderRequest;
import com.asyraf.whizware.application.dto.order.OrderDto;
import com.asyraf.whizware.domain.user.User;
import com.asyraf.whizware.exception.BadRequestException;
import com.asyraf.whizware.domain.item.Item;
import com.asyraf.whizware.domain.item.ItemBaseRepository;
import com.asyraf.whizware.domain.user.UserBaseRepository;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {

    @Inject
    OrderBaseRepository orderRepository;

    @Inject
    ItemBaseRepository itemRepository;

    @Inject
    UserBaseRepository userRepository;

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
        User user = userRepository.get(request.getUserId()).orElseThrow(() -> new BadRequestException("User not found!"));
        return Response.<OrderDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(orderRepository.save(Order.builder()
                .item(item)
                .user(user)
                .quantity(request.getQuantity())
                .price(item.getPrice())
                .totalPrice(item.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
                .build()).toDto())
            .build();
    }

    public Response<OrderDto> updateOrder(UUID id, OrderRequest request) throws Exception {
        Order order = orderRepository.get(id).orElseThrow(() -> new BadRequestException("Order not found!"));
        Item item = itemRepository.get(request.getItemId()).orElseThrow(() -> new BadRequestException("Item not found!"));
        User user = userRepository.get(request.getUserId()).orElseThrow(() -> new BadRequestException("User not found!"));
        order.setItem(item);
        order.setUser(user);
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
