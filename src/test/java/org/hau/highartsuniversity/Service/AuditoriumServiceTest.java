package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Auditorium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
class AuditoriumServiceTest {

    @Autowired
    AuditoriumService service;

    private final int NUMBER = 500;

    @Test
    void insert() {
        Auditorium.AuditoriumBuilder auditoriumBuilder = Auditorium.builder();
        Auditorium auditorium = service.insert(auditoriumBuilder.number(NUMBER).build());
        Assertions.assertEquals(NUMBER, service.getById(String.valueOf(auditorium.getId())).getNumber());
    }

    @Test
    void delete() {
        Auditorium.AuditoriumBuilder auditoriumBuilder = Auditorium.builder();
        Auditorium auditorium = auditoriumBuilder.build();

        Assertions.assertNotNull(service.insert(auditorium));
        service.delete(String.valueOf(auditorium.getId()));

        Assertions.assertThrows(NoSuchElementException.class, () ->
                service.getById(String.valueOf(auditorium.getId()))
        );

    }

    @Test
    void findAll() {
        Assertions.assertFalse(service.findAll().isEmpty());
    }

    @Test
    void getById() {
        Auditorium.AuditoriumBuilder auditoriumBuilder = Auditorium.builder();

        Assertions.assertEquals(NUMBER, service.getById(
                        String.valueOf(
                                service.insert(
                                        auditoriumBuilder.number(NUMBER).build()).getId()))
                .getNumber());
    }

    @Test
    void update() {
        Auditorium.AuditoriumBuilder auditoriumBuilder = Auditorium.builder();
        Auditorium auditorium = service.getById(
                String.valueOf(
                        service.insert(
                                        auditoriumBuilder.number(NUMBER).build())
                                .getId()));

        auditorium.setNumber(NUMBER + 1);
        service.update(String.valueOf(auditorium.getId()), auditorium);

        Assertions.assertNotEquals(NUMBER, service.getById(String.valueOf(auditorium.getId())).getNumber());


    }
}