package org.hau.highartsuniversity.Service;

import lombok.extern.slf4j.Slf4j;
import org.hau.highartsuniversity.Entity.Student;
import org.hau.highartsuniversity.Entity.Timetable;
import org.hau.highartsuniversity.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class StudentService {

    @Autowired
    StudentRepository repository;

    public Student insert(Student student) {
        return repository.save(student);
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        repository.findAll().forEach(studentList::add);
        return studentList;
    }

    public Student getById(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }

    public void update(String id, Student student) {
        Student dbStudent = repository.findById(Long.parseLong(id)).get();
        dbStudent.setGroup(student.getGroup());
        dbStudent.setStudyYear(student.getStudyYear());
        dbStudent.setFullName(student.getFullName());
        insert(dbStudent);
    }

    public Timetable getTimetableByDate(String studentId, String date) {
        Set<Timetable> timetableSet = getById(studentId).getGroup().getTimetable();
        for (Timetable timetable : timetableSet) {
            if (timetable.getDate().isEqual(LocalDate.parse(date))) {
                return timetable;

            }
        }
        return null;
    }
}
