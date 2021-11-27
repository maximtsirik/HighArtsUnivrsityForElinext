package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Auditorium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepository extends CrudRepository<Auditorium, Long> {
}
