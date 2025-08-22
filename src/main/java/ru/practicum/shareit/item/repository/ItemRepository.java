package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

/**
 * Класс-хранилище данных о вещах
 */
public interface ItemRepository {
    /**
     * Метод создаёт новую вещь и сохраняет её в хранилище
     * @param item - сохраняемая вещь
     * @param userId - идентификатор пользователя
     * @return возвращает сохранённую вещь
     */
    ItemDto createItem(Long userId, ItemDto item);

    /**
     * Метод проверяет, принадлежит ли вещь пользователю
     * @param userId - идентификатор пользователя
     * @param itemId - идентификатор вещи
     * @return возвращает true или false
     */
    boolean isUsersItem(Long userId, Long itemId);

    /**
     * Метод обновляет данные о вещи в хранилище
     * @param item - обновляемая вещь
     * @return возвращает обновлённую вещь
     */
    ItemDto updateItem(Long itemId, ItemDto item);

    /**
     * Метод возвращает вещь по её идентификатору
     * @param id - идентификатор вещи
     * @return возвращает найденную вещь из хранилища
     */
    ItemDto getItemById(Long id);

    /**
     * Метод удаляет вещь из хранилища по её идентификатору
     * @param id - идентификатор вещи
     */
    void deleteItemById(Long id);

    /**
     * Метод возвращает список вещей определённого пользователя
     * @param id - идентификатор пользователя
     * @return возвращает список с вещами
     */
    List<ItemDto> getItemsByUserId(Long id);

    /**
     * Метод поиска вещи в хранилище по ключевым словам
     * @param text - ключевые слова, по которым идёт поиск
     * @return возвращает список вещей, в которых найдены ключевые слова
     */
    List<ItemDto> search(String text);
}
