package org.hau.highartsuniversity;

import org.hau.highartsuniversity.Controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HighArtsUniversityApplicationTest {
    @Autowired
    StudentController studentController;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(studentController);
    }

}