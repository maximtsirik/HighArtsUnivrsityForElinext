package org.hau.highartsuniversity.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LessonType {

    LECTURE("Lecture"),
    PRACTICAL("Practical"),
    LABORATORY("laboratory");

    private String type;
}
