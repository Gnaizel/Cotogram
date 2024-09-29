package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String homePage() {
        return "<h1>Приветствуем вас, в приложении Котограм<h1>";
    }
}
