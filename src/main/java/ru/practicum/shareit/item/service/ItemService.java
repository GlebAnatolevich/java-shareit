package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

/**
 * Класс-сервис для управления сущностями вещей
 */
public interface ItemService {
    /**
     * Метод создаёт новую вещь и сохраняет её в хранилище
     * @param userId - идентификатор пользователя
     * @param item - сохраняемая вещь
     * @return возвращает сохранённую вещь
     */
    ItemDto createItem(Long userId, ItemDto item);

    /**
     * Метод обновляет данные о вещи в хранилище
     * @param userId - идентификатор пользователя
     * @param item - обновляемая вещь
     * @return возвращает обновлённую вещь
     */
    ItemDto updateItem(Long userId, Long itemId, ItemDto item);

    /**
     * Метод возвращает вещь по её идентификатору
     * @param userId - идентификатор пользователя
     * @param id - идентификатор вещи
     * @return возвращает найденную вещь из хранилища
     */
    ItemDto getItemById(Long userId, Long id);

    /**
     * Метод удаляет вещь из хранилища по её идентификатору
     * @param userId - идентификатор пользователя
     * @param id - идентификатор вещи
     */
    void deleteItemById(Long userId, Long id);

    /**
     * Метод возвращает список вещей определённого пользователя
     * @param id - идентификатор пользователя
     * @return возвращает список с вещами
     */
    List<ItemDto> getItemsByUserId(Long id);

    /**
     * Метод поиска вещей в хранилище по ключевым словам
     * @param text - ключевые слова, по которым идёт поиск
     * @return возвращает список вещей, в которых найдены ключевые слова
     */
    List<ItemDto> search(String text);
}
