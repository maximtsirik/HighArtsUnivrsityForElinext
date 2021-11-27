package org.hau.highartsuniversity.Controller;

import org.hau.highartsuniversity.Entity.Timetable;
import org.hau.highartsuniversity.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableController {

    @Autowired
    TimetableService timetableService;

    @GetMapping
    public ResponseEntity<List<Timetable>> getAll() {
        List<Timetable> timetableList = timetableService.findAll();
        return new ResponseEntity<>(timetableList, HttpStatus.OK);
    }

    @GetMapping({"/{timetableId}"})
    public ResponseEntity<Timetable> get(@PathVariable String timetableId) {
        return new ResponseEntity<>(timetableService.getById(timetableId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Timetable> save(@RequestBody Timetable timetable) {
        Timetable timetable1 = timetableService.insert(timetable);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("timetable", "/api/v1/timetable/" + timetable1.getId());
        return new ResponseEntity<>(timetable1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{timetableId}"})
    public ResponseEntity<Timetable> update(@PathVariable String timetableId, @RequestBody Timetable timetable) {
        timetableService.update(timetableId, timetable);
        return new ResponseEntity<>(timetableService.getById(timetableId), HttpStatus.OK);
    }

    @DeleteMapping({"/{timetableId}"})
    public ResponseEntity<Timetable> delete(@PathVariable String timetableId) {
        timetableService.delete(timetableId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
