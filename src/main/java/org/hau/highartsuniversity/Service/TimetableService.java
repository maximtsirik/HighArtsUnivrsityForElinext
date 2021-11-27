package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Timetable;
import org.hau.highartsuniversity.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    TimetableRepository repository;

    public Timetable insert(Timetable timetable) {
        return repository.save(timetable);
    }

    public Timetable getById(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public List<Timetable> findAll() {
        List<Timetable> timetableList = new ArrayList<>();
        repository.findAll().forEach(timetableList::add);
        return timetableList;
    }

    public void update(String id, Timetable timetable) {
        Timetable dbTimetable = getById(id);
        dbTimetable.setDate(timetable.getDate());
        dbTimetable.setGroup(timetable.getGroup());
        dbTimetable.setLessons(timetable.getLessons());
        insert(dbTimetable);
    }
}
