package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс-модель для создания сущности пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @PositiveOrZero
    private Long id;
    private String name;
    @NotBlank
    @Email(message = "Невалидный почтовый ящик")
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
