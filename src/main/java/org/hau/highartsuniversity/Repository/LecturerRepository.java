package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Lecturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer, Long> {
}
