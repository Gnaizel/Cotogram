package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Image {
    Long id;
    long postId;
    String originalFileName;
    String filePath;
}
