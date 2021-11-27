package org.hau.highartsuniversity.Controller;

import org.hau.highartsuniversity.Entity.Group;
import org.hau.highartsuniversity.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        List<Group> groupList = groupService.findAll();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @GetMapping({"/{groupId}"})
    public ResponseEntity<Group> get(@PathVariable String groupId) {
        return new ResponseEntity<>(groupService.getById(groupId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> save(@RequestBody Group group) {
        Group group1 = groupService.insert(group);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("group", "/api/v1/group/" + group1.getId());
        return new ResponseEntity<>(group1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{groupId}"})
    public ResponseEntity<Group> update(@PathVariable String groupId, @RequestBody Group group) {
        groupService.update(groupId, group);
        return new ResponseEntity<>(groupService.getById(groupId), HttpStatus.OK);
    }

    @DeleteMapping({"/{groupId}"})
    public ResponseEntity<Group> delete(@PathVariable String groupId) {
        groupService.delete(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
