package com.jiuzhao73.controller;

import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

@SpringBootTest
class MaintainerLoginControllerTest {
    @Autowired
    private MaintainerLoginController maintainerLoginController;

    @Test
    void login() {
        MaintainLoginInfo maintainLoginInfo=new MaintainLoginInfo(1,"123456");
        HttpSession httpSession=new MockHttpSession();
        System.out.println(maintainerLoginController.login(maintainLoginInfo, httpSession));
    }
}
