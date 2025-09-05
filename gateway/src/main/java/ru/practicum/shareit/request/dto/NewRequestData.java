package ru.practicum.shareit.request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NewRequestData {

    @NotBlank @Length(max = 512, message = "Текст запроса ограничен длиной 512 символов")
    String description;

    @Null(message = "Недопустимые данные времени создания запроса")
    LocalDateTime created;
}
