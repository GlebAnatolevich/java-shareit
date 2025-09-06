package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ItemRequestWithAnswersDto {

    @Data
    @AllArgsConstructor
    public static class ItemAnswer {
        long id;
        String name;
        long ownerId;

        public static ItemAnswer fromItem(Item item) {
            return new ItemAnswer(item.getId(), item.getName(), item.getOwner().getId());
        }
    }

    private long id;
    private String description;

    private LocalDateTime created;

    private final List<ItemAnswer> items = new ArrayList<>();
}
