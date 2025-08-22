package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ObjectNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemDto createItem(Long userId, ItemDto itemDto) {
        if (userRepository.getById(userId) == null) {
            throw new ObjectNotFoundException("Пользователь не найден");
        }
        Item item = ItemMapper.toItem(itemDto);
        Item itemCreated = itemRepository.createItem(userId, item);
        return ItemMapper.toItemDto(itemCreated);
    }

    @Override
    public ItemDto updateItem(Long userId, Long itemId, ItemDto itemDto) {
        if (userRepository.getById(userId) == null) {
            throw new ObjectNotFoundException("Пользователь не найден");
        }
        if (itemRepository.getItemById(itemId) == null) {
            throw new ObjectNotFoundException("Вещь не найдена");
        }
        if (!itemRepository.isUsersItem(userId, itemId)) {
            throw new ObjectNotFoundException("Пользователь не является владельцем товара");
        }
        Item item = ItemMapper.toItem(itemDto);
        Item updateItem = itemRepository.getItemById(itemId);
        if (item.getName() != null) {
            updateItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            updateItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            updateItem.setAvailable(item.getAvailable());
        }
        Item itemUpdated = itemRepository.updateItem(itemId, updateItem);
        return ItemMapper.toItemDto(itemUpdated);
    }

    @Override
    public ItemDto getItemById(Long userId, Long id) {
        if (userRepository.getById(userId) == null) {
            throw new ObjectNotFoundException("Пользователь не найден");
        }
        if (!itemRepository.existById(id)) {
            throw new ObjectNotFoundException(format("Вещь с идентификатором %d не найдена", id));
        }
        Item itemGet = itemRepository.getItemById(id);
        return ItemMapper.toItemDto(itemGet);
    }

    @Override
    public void deleteItemById(Long userId, Long id) {
        itemRepository.deleteItemById(id);
    }

    @Override
    public List<ItemDto> getItemsByUserId(Long id) {
        List<Item> itemsByUserId = itemRepository.getItemsByUserId(id);
        return itemsByUserId.stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    @Override
    public List<ItemDto> search(String text) {
        if (text.isBlank()) {
            return new ArrayList<>();
        } else {
            List<Item> itemsSearched = itemRepository.search(text);
            return itemsSearched.stream()
                    .map(ItemMapper::toItemDto)
                    .toList();
        }
    }
}
