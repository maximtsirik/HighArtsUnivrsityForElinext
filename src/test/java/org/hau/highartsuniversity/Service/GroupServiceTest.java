package org.hau.highartsuniversity.Service;

import org.hau.highartsuniversity.Entity.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
class GroupServiceTest {
    private final int NUMBER = 10802115;

    @Autowired
    GroupService service;

    @Test
    void insert() {
        Group.GroupBuilder groupBuilder = Group.builder();
        Group group = service.insert(groupBuilder.number(NUMBER).build());
        Assertions.assertEquals(NUMBER, service.getById(String.valueOf(group.getId())).getNumber());
    }

    @Test
    void delete() {
        Group.GroupBuilder groupBuilder = Group.builder();
        Group group = groupBuilder.build();

        Assertions.assertNotNull(service.insert(group));

        service.delete(String.valueOf(group.getId()));

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            service.getById(String.valueOf(group.getId()));
        });
    }

    @Test
    void findAll() {
        Assertions.assertFalse(service.findAll().isEmpty());
    }

    @Test
    void getById() {
        Group.GroupBuilder groupBuilder = Group.builder();

        Assertions.assertEquals(NUMBER, service.getById(
                        String.valueOf(
                                service.insert(
                                        groupBuilder.number(NUMBER).build()).getId()))
                .getNumber());
    }

    @Test
    void update() {
        Group.GroupBuilder groupBuilder = Group.builder();
        Group group = service.getById(
                String.valueOf(
                        service.insert(
                                        groupBuilder.number(NUMBER).build())
                                .getId()));

        group.setNumber(NUMBER + 1);
        service.update(String.valueOf(group.getId()), group);

        Assertions.assertNotEquals(NUMBER, service.getById(String.valueOf(group.getId())).getNumber());
    }
}