package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс-модель DTO для создания объекта пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @PositiveOrZero
    private Long id;
    private String name;
    @Email(message = "Невалидный почтовый ящик")
    private String email;
}
