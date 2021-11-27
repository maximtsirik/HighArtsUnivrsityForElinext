package org.hau.highartsuniversity.Controller;

import org.hau.highartsuniversity.Entity.Lesson;
import org.hau.highartsuniversity.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getAll() {
        List<Lesson> lessonList = lessonService.findAll();
        return new ResponseEntity<>(lessonList, HttpStatus.OK);
    }

    @GetMapping({"/{lessonId}"})
    public ResponseEntity<Lesson> get(@PathVariable String lessonId) {
        return new ResponseEntity<>(lessonService.getById(lessonId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lesson> save(@RequestBody Lesson lesson) {
        Lesson lesson1 = lessonService.insert(lesson);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("lesson", "/api/v1/lesson/" + lesson1.getId());
        return new ResponseEntity<>(lesson1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{lessonId}"})
    public ResponseEntity<Lesson> update(@PathVariable String lessonId, @RequestBody Lesson lesson) {
        lessonService.update(lessonId, lesson);
        return new ResponseEntity<>(lessonService.getById(lessonId), HttpStatus.OK);
    }

    @DeleteMapping({"/{lessonId}"})
    public ResponseEntity<Lesson> delete(@PathVariable String lessonId) {
        lessonService.delete(lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
