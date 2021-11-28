package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByFullName(String fullName);
}
