package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository repository;
    private final String NAME = "Name";

    @Test
    public void save() {
        Student.StudentBuilder studentBuilder = Student.builder();
        Student student = repository.save(studentBuilder.fullName(NAME).build());

        Assertions.assertEquals(repository.findById(student.getId()).get().getFullName(), NAME);

    }

    @Test
    public void findByName() {
        Student.StudentBuilder studentBuilder = Student.builder();
        repository.save(studentBuilder.fullName(NAME).build());
        Assertions.assertEquals(repository.findByFullName(NAME).get().getFullName(), NAME);
    }

    @Test
    public void update() {
        Student.StudentBuilder studentBuilder = Student.builder();
        Student student = repository.save(studentBuilder.fullName(NAME).build());

        Student student1 = repository.findByFullName(student.getFullName()).get();
        student1.setStudyYear(3);
        repository.save(student1);

        Assertions.assertEquals(repository.findByFullName(NAME).get().getStudyYear(), 3);

    }

    @Test
    public void delete() {
        Student.StudentBuilder studentBuilder = Student.builder();
        repository.save(studentBuilder.fullName(NAME).build());

        assertTrue(repository.findByFullName(NAME).isPresent());

        repository.delete(
                repository.findByFullName(NAME).get());

        assertThrows(NoSuchElementException.class, () -> {
            repository.findByFullName(NAME).get();
        });

    }

}