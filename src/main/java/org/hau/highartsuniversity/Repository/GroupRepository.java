package org.hau.highartsuniversity.Repository;

import org.hau.highartsuniversity.Entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
