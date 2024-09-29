package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    private final Map<Long, User> users = new HashMap<>();

    public Set<User> getUsers() {
        return new HashSet<>(users.values());
    }

    public User findUserForId(Long id) {
        return getUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id %d не найден", id)));
    }

    public Optional<User> findUserById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User createUser(User user) {
        if (user.getEmail().isBlank()) throw new ConditionsNotMetException("Имейл должен быть указан");

        if (users.containsValue(user)) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }

        if (user.getPassword() == null || user.getUsername() == null) {
            users.replace(user.getId(), user);
        }

        user.setId(getNextId());
        user.setRegistrationDate(Instant.now());

        users.put(user.getId(), user);
        return user;
    }

    public User redactUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

        if (users.containsValue(user)) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }

        User existingUser = users.get(user.getId());

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        users.replace(existingUser.getId(), existingUser);
        return existingUser;
    }

    private Long getNextId() {
        long counterMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++counterMaxId;
    }
}
