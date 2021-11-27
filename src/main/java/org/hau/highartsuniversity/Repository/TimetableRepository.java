package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Long> {
}
