package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Group;
import org.hau.highartsuniversity.Entity.Student;
import org.hau.highartsuniversity.Entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    StudentService service;
    @Autowired
    TimetableService timetableService;
    @Autowired
    GroupService groupService;

    private final String NAME = "Thorin";

    @Test
    void insert() {
        Student.StudentBuilder studentBuilder = Student.builder();
        Student student = service.insert(studentBuilder.fullName(NAME).build());
        assertEquals(NAME, service.getById(String.valueOf(student.getId())).getFullName());
    }

    @Test
    void delete() {
        Student.StudentBuilder studentBuilder = Student.builder();
        Student student = studentBuilder.build();

        assertNotNull(service.insert(student));

        service.delete(String.valueOf(student.getId()));

        assertThrows(NoSuchElementException.class, () ->
                service.getById(String.valueOf(student.getId()))
        );
    }

    @Test
    void findAll() {
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    void getById() {
        Student.StudentBuilder studentBuilder = Student.builder();

        assertEquals(NAME, service.getById(
                        String.valueOf(
                                service.insert(
                                        studentBuilder.fullName(NAME).build()).getId()))
                .getFullName());
    }

    @Test
    void update() {
        Student.StudentBuilder studentBuilder = Student.builder();
        Student student = service.getById(
                String.valueOf(
                        service.insert(
                                        studentBuilder.fullName(NAME).build())
                                .getId()));

        student.setFullName(NAME + " Oakshield");
        service.update(String.valueOf(student.getId()), student);

        assertNotEquals(NAME, service.getById(String.valueOf(student.getId())).getFullName());
    }

    @Test
    void getTimetableByDate() {
        Student.StudentBuilder studentBuilder = Student.builder();
        Student student = service.insert(studentBuilder.build());

        Group.GroupBuilder groupBuilder = Group.builder();
        Group group = groupService.insert(groupBuilder.students(new HashSet<>()).timetable(new HashSet<>()).build());

        group.getStudents().add(student);
        student.setGroup(group);

        service.insert(student);
        groupService.insert(group);

        Timetable.TimetableBuilder timetableBuilder = Timetable.builder();
        LocalDate date = LocalDate.of(2021, 9, 1);
        Timetable timetable = timetableService.insert(timetableBuilder.date(date).build());

        group.getTimetable().add(timetable);
        timetable.setGroup(group);

        groupService.insert(group);
        timetableService.insert(timetable);

        assertEquals(date, service.getTimetableByDate(String.valueOf(student.getId()), date.toString()).getDate());


    }
}