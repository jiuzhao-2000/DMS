package com.jiuzhao73.service.impl;

import com.jiuzhao73.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentInfoServiceImplTest {
    @Autowired
    private StudentService studentService;

    @Test
    void getStudentInfo() {
        System.out.println(studentService.getStudentInfo("1000000000"));
    }
}
