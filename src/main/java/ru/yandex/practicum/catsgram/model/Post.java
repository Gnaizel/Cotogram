package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
public class Post {
    Long id;
    long authorId;
    String description;
    Instant postDate;
}
