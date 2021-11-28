package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Lecturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LecturerServiceTest {
    @Autowired
    LecturerService service;
    private final String NAME = "Lebowski";

    @Test
    void insert() {
        Lecturer.LecturerBuilder lecturerBuilder = Lecturer.builder();
        Lecturer lecturer = service.insert(lecturerBuilder.fullName(NAME).build());
        Assertions.assertEquals(NAME, service.getById(String.valueOf(lecturer.getId())).getFullName());
    }

    @Test
    void delete() {
        Lecturer.LecturerBuilder lecturerBuilder = Lecturer.builder();
        Lecturer lecturer = lecturerBuilder.build();

        assertNotNull(service.insert(lecturer));

        service.delete(String.valueOf(lecturer.getId()));

        assertThrows(NoSuchElementException.class, () ->
                service.getById(String.valueOf(lecturer.getId()))
        );
    }

    @Test
    void findAll() {
        Assertions.assertFalse(service.findAll().isEmpty());
    }

    @Test
    void getById() {
        Lecturer.LecturerBuilder lecturerBuilder = Lecturer.builder();

        Assertions.assertEquals(NAME, service.getById(
                        String.valueOf(
                                service.insert(
                                        lecturerBuilder.fullName(NAME).build()).getId()))
                .getFullName());
    }

    @Test
    void update() {
        Lecturer.LecturerBuilder lecturerBuilder = Lecturer.builder();
        Lecturer lecturer = service.getById(
                String.valueOf(
                        service.insert(
                                        lecturerBuilder.fullName(NAME).build())
                                .getId()));

        lecturer.setFullName("The Dude " + NAME);
        service.update(String.valueOf(lecturer.getId()), lecturer);

        Assertions.assertNotEquals(NAME, service.getById(String.valueOf(lecturer.getId())).getFullName());
    }
}