package com.jiuzhao73.service.impl;

import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.service.StudentLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentLoginServiceImplTest {
    @Autowired
    private StudentLoginService studentLoginService;

    @Test
    void isStudent() {
        StudentLoginInfo student = new StudentLoginInfo();
        student.setSid("1000000000");
        student.setPwd("123456");
        System.out.println(studentLoginService.login(student));

    }
}
