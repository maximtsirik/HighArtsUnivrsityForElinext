package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LessonServiceTest {
    @Autowired
    LessonService service;
    private final String NAME = "Math";

    @Test
    void insert() {
        Lesson.LessonBuilder lessonBuilder = Lesson.builder();
        Lesson lesson = service.insert(lessonBuilder.name(NAME).build());
        assertEquals(NAME, service.getById(String.valueOf(lesson.getId())).getName());

    }

    @Test
    void delete() {
        Lesson.LessonBuilder lessonBuilder = Lesson.builder();
        Lesson lesson = lessonBuilder.build();

        assertNotNull(service.insert(lesson));

        service.delete(String.valueOf(lesson.getId()));

        assertThrows(NoSuchElementException.class, () ->
                service.getById(String.valueOf(lesson.getId()))
        );
    }

    @Test
    void findAll() {
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    void getById() {
        Lesson.LessonBuilder lessonBuilder = Lesson.builder();

        assertEquals(NAME, service.getById(
                        String.valueOf(
                                service.insert(
                                        lessonBuilder.name(NAME).build()).getId()))
                .getName());
    }

    @Test
    void update() {
        Lesson.LessonBuilder lessonBuilder = Lesson.builder();
        Lesson lesson = service.getById(
                String.valueOf(
                        service.insert(
                                        lessonBuilder.name(NAME).build())
                                .getId()));

        lesson.setName("High " + NAME);
        service.update(String.valueOf(lesson.getId()), lesson);

        assertNotEquals(NAME, service.getById(String.valueOf(lesson.getId())).getName());
    }
}