package org.hau.highartsuniversity.Controller;

import org.hau.highartsuniversity.Entity.Student;
import org.hau.highartsuniversity.Entity.Timetable;
import org.hau.highartsuniversity.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}/timetable/{date}")
    public ResponseEntity<Timetable> getTimetableByDate(@PathVariable String studentId,
                                                        @PathVariable String date) {
        return new ResponseEntity<>(studentService.getTimetableByDate(studentId, date), HttpStatus.OK);
    }

    @GetMapping("/{studentId}/timetable")
    public ResponseEntity<Set<Timetable>> getTimetable(@PathVariable String studentId) {
        return new ResponseEntity<>(studentService.getTimetable(studentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping({"/{studentId}"})
    public ResponseEntity<Student> get(@PathVariable String studentId) {
        return new ResponseEntity<>(studentService.getById(studentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student student1 = studentService.insert(student);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("student", "/api/v1/student/" + student1.getId());
        return new ResponseEntity<>(student1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{studentId}"})
    public ResponseEntity<Student> update(@PathVariable String studentId, @RequestBody Student student) {
        studentService.update(studentId, student);
        return new ResponseEntity<>(studentService.getById(studentId), HttpStatus.OK);
    }

    @DeleteMapping({"/{studentId}"})
    public ResponseEntity<Student> delete(@PathVariable String studentId) {
        studentService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
