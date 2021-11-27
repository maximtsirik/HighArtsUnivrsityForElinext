package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Auditorium;
import org.hau.highartsuniversity.Repository.AuditoriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditoriumService {

    @Autowired
    AuditoriumRepository repository;

    public Auditorium insert(Auditorium auditorium) {
        return repository.save(auditorium);
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public List<Auditorium> findAll() {
        List<Auditorium> auditoriumList = new ArrayList<>();
        repository.findAll().forEach(auditoriumList::add);
        return auditoriumList;
    }

    public Auditorium getById(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }

    public void update(String id, Auditorium auditorium) {
        Auditorium dbAuditorium = repository.findById(Long.parseLong(id)).get();
        dbAuditorium.setLesson(auditorium.getLesson());
        dbAuditorium.setNumber(auditorium.getNumber());
        repository.save(dbAuditorium);

    }
}
