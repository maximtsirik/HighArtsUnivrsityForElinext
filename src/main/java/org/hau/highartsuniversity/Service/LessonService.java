package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Lesson;
import org.hau.highartsuniversity.Repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    LessonRepository repository;

    public Lesson insert(Lesson lesson) {
        return repository.save(lesson);
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public List<Lesson> findAll() {
        List<Lesson> lessonList = new ArrayList<>();
        repository.findAll().forEach(lessonList::add);
        return lessonList;
    }

    public Lesson getById(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }

    public void update(String id, Lesson lesson) {
        Lesson dbLesson = getById(id);
        dbLesson.setLessonType(lesson.getLessonType());
        dbLesson.setAuditorium(lesson.getAuditorium());
        dbLesson.setLecturers(lesson.getLecturers());
        dbLesson.setName(lesson.getName());
        dbLesson.setTimetables(lesson.getTimetables());
        insert(dbLesson);
    }
}
