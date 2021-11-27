package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
