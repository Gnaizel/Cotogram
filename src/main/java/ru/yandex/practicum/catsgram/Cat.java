package ru.yandex.practicum.catsgram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Cat{
    private String color;
    private int age;

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}