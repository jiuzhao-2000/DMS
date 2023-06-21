package com.jiuzhao73.controller;

import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@SpringBootTest
class StudentSelfInfoControllerTest {
    @Autowired
    private StudentLoginController studentLoginController;
    @Autowired
    private StudentSelfInfoController studentSelfInfoController;

    @Test
    void getStudentInfo() {
        StudentLoginInfo studentLoginInfo = new StudentLoginInfo("1000000000", "123456");
        HttpSession httpSession=new MockHttpSession();
        Model m=new ConcurrentModel();
        System.out.println(studentLoginController.login(studentLoginInfo,httpSession));
        System.out.println(studentSelfInfoController.queryStudentInfo(m));
    }
}
