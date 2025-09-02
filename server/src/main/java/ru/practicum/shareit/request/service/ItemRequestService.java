package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestWithAnswersDto;
import ru.practicum.shareit.request.dto.NewItemRequestDto;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto createItemRequest(NewItemRequestDto newItemRequestDto);

    List<ItemRequestWithAnswersDto> getUserItemRequests(long userId);

    List<ItemRequestDto> getOtherUsersItemRequests(long userId);

    ItemRequestWithAnswersDto getItemRequestById(long requestId);
}
