package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Group;
import org.hau.highartsuniversity.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository repository;

    public Group insert(Group group) {
        return repository.save(group);
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public List<Group> findAll() {
        List<Group> groupList = new ArrayList<>();
        repository.findAll().forEach(groupList::add);
        return groupList;
    }

    public Group getById(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }

    public void update(String id, Group group) {
        Group dbGroup = repository.findById(Long.parseLong(id)).get();
        dbGroup.setNumber(group.getNumber());
        dbGroup.setStudents(group.getStudents());
        dbGroup.setTimetable(group.getTimetable());
        repository.save(dbGroup);
    }
}
