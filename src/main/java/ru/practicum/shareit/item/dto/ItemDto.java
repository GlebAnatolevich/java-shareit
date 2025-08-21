package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.model.User;

/**
 * Класс-маппер для создания объекта вещи.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

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

    public ItemDto(String name, String description, Boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }
}
