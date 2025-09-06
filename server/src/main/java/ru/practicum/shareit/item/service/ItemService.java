package ru.practicum.shareit.item.service;

import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.comments.dto.NewCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemWithCommentsDto;
import ru.practicum.shareit.item.dto.NewItemDto;
import ru.practicum.shareit.item.dto.UpdateItemDto;

import java.util.List;

public interface ItemService {
    ItemWithCommentsDto getItem(long itemId, long userId);

    ItemDto addItem(NewItemDto newItemDto);

    ItemDto updateItem(UpdateItemDto updateItemDto);

    List<ItemWithCommentsDto> getItemsOfUser(long userId);

    List<ItemDto> searchItems(long userId, String searchPattern);

    CommentDto addComment(NewCommentDto request);
}
