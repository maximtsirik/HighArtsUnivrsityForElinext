package org.hau.highartsuniversity.Controller;

import org.hau.highartsuniversity.Entity.Lecturer;
import org.hau.highartsuniversity.Service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lecturer")
public class LecturerController {

    @Autowired
    LecturerService lecturerService;

    @GetMapping
    public ResponseEntity<List<Lecturer>> getAll() {
        List<Lecturer> lecturerList = lecturerService.findAll();
        return new ResponseEntity<>(lecturerList, HttpStatus.OK);
    }

    @GetMapping({"/{lecturerId}"})
    public ResponseEntity<Lecturer> get(@PathVariable String lecturerId) {
        return new ResponseEntity<>(lecturerService.getById(lecturerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lecturer> save(@RequestBody Lecturer lecturer) {
        Lecturer lecturer1 = lecturerService.insert(lecturer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("lecturer", "/api/v1/lecturer/" + lecturer1.getId());
        return new ResponseEntity<>(lecturer1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{lecturerId}"})
    public ResponseEntity<Lecturer> update(@PathVariable String lecturerId, @RequestBody Lecturer lecturer) {
        lecturerService.update(lecturerId, lecturer);
        return new ResponseEntity<>(lecturerService.getById(lecturerId), HttpStatus.OK);
    }

    @DeleteMapping({"/{lecturerId}"})
    public ResponseEntity<Lecturer> delete(@PathVariable String lecturerId) {
        lecturerService.delete(lecturerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
