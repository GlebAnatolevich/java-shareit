package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.util.Create;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemShortDto {
    private Long id;
    @NotBlank(message = "Поле названия вещи не должно быть пустым", groups = {Create.class})
    private String name;
    @NotNull(message = "Поле описания вещи не должно быть пустым", groups = {Create.class})
    private String description;
    @NotNull(message = " Поле статуса доступности вещи не может быть пустым", groups = {Create.class})
    private Boolean available;
}
