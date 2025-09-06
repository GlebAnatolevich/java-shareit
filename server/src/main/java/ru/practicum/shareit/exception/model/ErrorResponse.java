package ru.practicum.shareit.exception.model;

/**
 * Класс-модель для создания объекта ответа ошибки
 *
 * @param error - строка с сообщением ошибки
 */
public record ErrorResponse(String error) {
}
