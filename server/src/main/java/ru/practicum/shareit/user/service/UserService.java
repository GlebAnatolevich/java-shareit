package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.NewUserDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(long id);

    UserDto addUser(NewUserDto newUserRequest);

    UserDto updateUser(long id, NewUserDto userData);

    List<UserDto> getUsers();

    void deleteUser(long id);
}
