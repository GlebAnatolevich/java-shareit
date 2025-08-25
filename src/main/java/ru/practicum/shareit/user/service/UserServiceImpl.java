package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ObjectAlreadyExistsException;
import ru.practicum.shareit.exception.ObjectNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        if (user.getEmail() == null) {
            throw new ValidationException("У пользователя отсутствует адрес email");
        }
        if (repository.existByEmail(user.getEmail())) {
            throw new ObjectAlreadyExistsException("Пользователь с таким email уже существует");
        }
        User userCreated = repository.create(user);
        return UserMapper.toUserDto(userCreated);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        if (repository.getById(id) == null) {
            throw new ObjectNotFoundException("Пользователь не найден");
        }
        User user = UserMapper.toUser(userDto);
        if (repository.existByEmail(user.getEmail())) {
            throw new ObjectAlreadyExistsException("Пользователь с таким email уже существует");
        }
        User updateUser = repository.getById(id);
        if (user.getName() != null) {
            updateUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            updateUser.setEmail(user.getEmail());
        }
        User userUpdated = repository.update(id, updateUser);
        return UserMapper.toUserDto(userUpdated);
    }

    @Override
    public UserDto getById(Long id) {
        if (!repository.existById(id)) {
            throw new ObjectNotFoundException("Ошибка! Пользователь с идентификатором " + id + " отсутствует в " +
                    "хранилище");
        }
        User userGet = repository.getById(id);
        return UserMapper.toUserDto(userGet);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<UserDto> getUsers() {
        return repository.getUsers().stream()
                .map(UserMapper::toUserDto)
                .toList();
    }
}
