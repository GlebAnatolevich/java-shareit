package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.model.User;

/**
 * Класс-модель для создания сущности вещи.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @PositiveOrZero
    private Long id;
    @NotBlank(message = "Поле названия вещи не должно быть пустым")
    private String name;
    @NotBlank(message = "Поле описания вещи не должно быть пустым")
    private String description;
    @NotNull(message = "Поле статуса доступности не может быть пустым")
    private Boolean available;
    private User owner;
    private Boolean isRequested;
}
