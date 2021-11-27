package org.hau.highartsuniversity.Controller;

import org.hau.highartsuniversity.Entity.Auditorium;
import org.hau.highartsuniversity.Service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditorium")
public class AuditoriumController {

    @Autowired
    AuditoriumService auditoriumService;

    @GetMapping
    public ResponseEntity<List<Auditorium>> getAll() {
        List<Auditorium> auditoriumList = auditoriumService.findAll();
        return new ResponseEntity<>(auditoriumList, HttpStatus.OK);
    }

    @GetMapping({"/{auditoriumId}"})
    public ResponseEntity<Auditorium> get(@PathVariable String auditoriumId) {
        return new ResponseEntity<>(auditoriumService.getById(auditoriumId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Auditorium> save(@RequestBody Auditorium auditorium) {
        Auditorium auditorium1 = auditoriumService.insert(auditorium);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("auditorium", "/api/v1/auditorium/" + auditorium1.getId());
        return new ResponseEntity<>(auditorium1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{auditoriumId}"})
    public ResponseEntity<Auditorium> update(@PathVariable String auditoriumId, @RequestBody Auditorium auditorium) {
        auditoriumService.update(auditoriumId, auditorium);
        return new ResponseEntity<>(auditoriumService.getById(auditoriumId), HttpStatus.OK);
    }

    @DeleteMapping({"/{auditoriumId}"})
    public ResponseEntity<Auditorium> delete(@PathVariable String auditoriumId) {
        auditoriumService.delete(auditoriumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
