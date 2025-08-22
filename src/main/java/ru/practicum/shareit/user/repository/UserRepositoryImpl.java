package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long id = 0L;

    @Override
    public User create(User user) {
        user.setId(increment());
        users.put(user.getId(), user);
        return users.get(id);
    }

    @Override
    public User update(Long id, User user) {
        users.put(id, user);
        return users.get(id);
    }

    @Override
    public User getById(Long id) {
        return users.get(id);
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Boolean existById(Long id) {
        return users.containsKey(id);
    }

    @Override
    public Boolean existByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    private Long increment() {
        return ++id;
    }
}
