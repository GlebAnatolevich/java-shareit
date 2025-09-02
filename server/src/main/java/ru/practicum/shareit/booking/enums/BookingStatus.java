package ru.practicum.shareit.booking.enums;

public enum BookingStatus {
    /**
     * Новое бронирование, ожидающее одобрения
     */
    WAITING,

    /**
     * Подтверждённое владельцем бронирование
     */
    APPROVED,

    /**
     * Отклонённое владельцем бронирование
     */
    REJECTED,

    /**
     * Отклонённое создателем бронирование
     */
    CANCELED
}
