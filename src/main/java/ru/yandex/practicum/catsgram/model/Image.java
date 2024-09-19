package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Image {
    Long id;
    long postId;
    String originalFileName;
    String filePath;
}
