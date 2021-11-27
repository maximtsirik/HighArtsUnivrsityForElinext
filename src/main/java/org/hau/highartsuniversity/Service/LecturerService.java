package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Lecturer;
import org.hau.highartsuniversity.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LecturerService {

    @Autowired
    LecturerRepository repository;

    public Lecturer insert(Lecturer lecturer) {
        return repository.save(lecturer);
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public List<Lecturer> findAll() {
        List<Lecturer> lecturerList = new ArrayList<>();
        repository.findAll().forEach(lecturerList::add);
        return lecturerList;
    }

    public Lecturer getById(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }

    public void update(String id, Lecturer lecturer) {
        Lecturer dbLecturer = repository.findById(Long.parseLong(id)).get();
        dbLecturer.setLessons(lecturer.getLessons());
        dbLecturer.setFullName(lecturer.getFullName());
        repository.save(dbLecturer);

    }
}
