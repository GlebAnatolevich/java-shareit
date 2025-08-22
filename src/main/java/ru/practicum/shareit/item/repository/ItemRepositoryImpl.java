package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.ObjectNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.*;

import static java.lang.String.format;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, ItemDto> items = new HashMap<>();
    private final Map<Long, Set<Long>> usersItems = new HashMap<>();
    private Long id = 0L;

    @Override
    public ItemDto createItem(Long userId, ItemDto item) {
        Long id = increment();
        item.setId(id);
        item.setUserId(userId);
        items.put(id, item);
        saveUsersItems(userId, id);
        return items.get(id);
    }

    private void saveUsersItems(Long userId, Long id) {
        if (!usersItems.containsKey(userId)) {
            usersItems.put(userId, new TreeSet<>());
        }
        usersItems.get(userId).add(id);
    }

    @Override
    public boolean isUsersItem(Long userId, Long itemId) {
        return usersItems.get(userId).contains(itemId);
    }

    @Override
    public ItemDto updateItem(Long itemId, ItemDto item) {
        ItemDto thisItem = items.get(itemId);
        thisItem.setName(item.getName());
        thisItem.setDescription(item.getDescription());
        thisItem.setAvailable(item.getAvailable());
        return items.put(thisItem.getId(), thisItem);
    }

    @Override
    public ItemDto getItemById(Long id) {
        if (!items.containsKey(id)) {
            throw new ObjectNotFoundException(format("Вещь с идентификатором %d не найдена", id));
        }
        return items.get(id);
    }

    @Override
    public void deleteItemById(Long id) {
        items.remove(id);
        usersItems.keySet().forEach(key -> usersItems.get(key).remove(id));
    }

    @Override
    public List<ItemDto> getItemsByUserId(Long id) {
        return usersItems.get(id).stream()
                .map(items::get)
                .toList();
    }

    @Override
    public List<ItemDto> search(String text) {
        if (text.isBlank()) {
            return new ArrayList<>();
        } else {
            return items.values().stream()
                    .filter(item -> isContainsText(text, item))
                    .filter(ItemDto::getAvailable)
                    .toList();
        }
    }

    private boolean isContainsText(String text, ItemDto item) {
        String lowerCase = text.toLowerCase();
        return item.getName().toLowerCase().contains(lowerCase)
                || item.getDescription().toLowerCase().contains(lowerCase);
    }

    private Long increment() {
        return ++id;
    }
}
