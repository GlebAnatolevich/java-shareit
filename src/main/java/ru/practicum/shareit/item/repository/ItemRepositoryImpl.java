package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();
    private final Map<Long, Set<Long>> usersItems = new HashMap<>();
    private Long id = 0L;

    @Override
    public Item createItem(Long userId, Item item) {
        Long id = increment();
        item.setId(id);
        item.setUserId(userId);
        items.put(id, item);
        saveUsersItems(userId, id);
        return items.get(id);
    }

    @Override
    public boolean isUsersItem(Long userId, Long itemId) {
        return usersItems.get(userId).contains(itemId);
    }

    @Override
    public Item updateItem(Long itemId, Item item) {
        return items.put(item.getId(), item);
    }

    @Override
    public Item getItemById(Long id) {
        return items.get(id);
    }

    @Override
    public void deleteItemById(Long id) {
        items.remove(id);
        usersItems.keySet().forEach(key -> usersItems.get(key).remove(id));
    }

    @Override
    public List<Item> getItemsByUserId(Long id) {
        return usersItems.get(id).stream()
                .map(items::get)
                .toList();
    }

    @Override
    public List<Item> search(String text) {
        return items.values().stream()
                .filter(item -> isContainsText(text, item))
                .filter(Item::getAvailable)
                .toList();
    }

    @Override
    public Boolean existById(Long id) {
        return items.containsKey(id);
    }

    private void saveUsersItems(Long userId, Long id) {
        if (!usersItems.containsKey(userId)) {
            usersItems.put(userId, new TreeSet<>());
        }
        usersItems.get(userId).add(id);
    }

    private boolean isContainsText(String text, Item item) {
        String lowerCase = text.toLowerCase();
        return item.getName().toLowerCase().contains(lowerCase)
                || item.getDescription().toLowerCase().contains(lowerCase);
    }

    private Long increment() {
        return ++id;
    }
}
