package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingShortDto {
    private Long id;
    @NotNull(message = "Время создания заявки на бронирование не должно быть пустым")
    @FutureOrPresent(message = "Время начала заявки не должно быть в прошлом")
    private LocalDateTime start;
    @NotNull(message = "Время окончания заявки на бронирование не должно быть пустым")
    @Future(message = "Время окончания заявки не может быть в прошлом")
    private LocalDateTime end;
    @NotNull
    private Long itemId;
}
