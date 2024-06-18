package com.asyraf.whizware.domain.item;

import com.asyraf.whizware.infrastructure.response.Response;
import com.asyraf.whizware.application.item.ItemRequest;
import com.asyraf.whizware.application.item.ItemDto;
import com.asyraf.whizware.infrastructure.response.ListResponse;
import com.asyraf.whizware.exception.BadRequestException;
import com.asyraf.whizware.domain.order.OrderBaseRepository;
import com.google.inject.Inject;

import java.util.UUID;
import java.util.stream.Collectors;

public class ItemService {

    @Inject
    private ItemBaseRepository itemRepository;

    @Inject
    private OrderBaseRepository orderRepository;

    public ListResponse<ItemDto> getAllItem() {
        return ListResponse.<ItemDto>builder()
            .success(true)
            .message("Successfully!")
            .data(itemRepository.getAll().stream().map(Item::toDto).collect(Collectors.toList()))
            .build();
    }

    public Response<ItemDto> getItem(UUID id) throws BadRequestException {
        Item item = itemRepository.get(id).orElseThrow(() -> new BadRequestException("User not found!"));
        return Response.<ItemDto>builder()
            .success(true)
            .message("Successfully!")
            .data(item.toDto())
            .build();
    }

    public Response<ItemDto> addItem(ItemRequest request) throws Exception {
        Item item = Item.builder()
            .name(request.getName())
            .price(request.getPrice())
            .build();
        Item savedItem = itemRepository.save(item);
        return Response.<ItemDto>builder()
            .success(true)
            .message("Successfully save")
            .data(savedItem.toDto())
            .build();
    }

    public Response<ItemDto> updateItem(UUID id, ItemRequest request) throws Exception {
        Item item = itemRepository.get(id).orElseThrow(() -> new BadRequestException("User not found!"));
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        itemRepository.save(item);
        return Response.<ItemDto>builder()
            .success(true)
            .message("Successfully update")
            .data(item.toDto())
            .build();
    }

    public void deleteItem(UUID id) throws Exception {
        Item item = itemRepository.get(id).orElseThrow(() -> new BadRequestException("User not found!"));
        if (!orderRepository.getByItemId(item.getId()).isEmpty()) {
            throw new BadRequestException("Item is used in Order");
        }
        itemRepository.delete(item);
    }

}
