package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimetableServiceTest {
    @Autowired
    TimetableService service;

    private final LocalDate DATE = LocalDate.of(2021, 9, 1);

    @Test
    void insert() {
        Timetable.TimetableBuilder timetableBuilder = Timetable.builder();
        Timetable timetable = service.insert(timetableBuilder.date(DATE).build());
        assertEquals(DATE, service.getById(String.valueOf(timetable.getId())).getDate());
    }

    @Test
    void getById() {
        Timetable.TimetableBuilder timetableBuilder = Timetable.builder();

        assertEquals(DATE, service.getById(
                        String.valueOf(
                                service.insert(
                                        timetableBuilder.date(DATE).build()).getId()))
                .getDate());
    }

    @Test
    void delete() {
        Timetable.TimetableBuilder timetableBuilder = Timetable.builder();
        Timetable timetable = timetableBuilder.build();

        assertNotNull(service.insert(timetable));

        service.delete(String.valueOf(timetable.getId()));

        assertThrows(NoSuchElementException.class, () ->
                service.getById(String.valueOf(timetable.getId()))
        );
    }

    @Test
    void findAll() {
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    void update() {
        Timetable.TimetableBuilder timetableBuilder = Timetable.builder();
        Timetable timetable = service.getById(
                String.valueOf(
                        service.insert(
                                        timetableBuilder.date(DATE).build())
                                .getId()));

        timetable.setDate(DATE.plusDays(2));
        service.update(String.valueOf(timetable.getId()), timetable);

        assertNotEquals(DATE, service.getById(String.valueOf(timetable.getId())).getDate());
    }
}