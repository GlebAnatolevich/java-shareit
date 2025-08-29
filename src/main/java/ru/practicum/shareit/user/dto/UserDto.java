package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.util.Create;
import ru.practicum.shareit.util.Update;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Поле имени не должно быть пустым", groups = {Create.class})
    private String name;
    @Email(message = "Невалидный почтовый ящик", groups = {Create.class, Update.class})
    @NotBlank(message = "Поле почтового ящика не должно быть пустым", groups = {Create.class})
    private String email;
}
