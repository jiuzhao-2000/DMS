package com.jiuzhao73.controller;

import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdministrationLoginControllerTest {
    @Autowired
    private AdministrationLoginController administrationLoginController;

    @Test
    void login() {
        HttpSession session = new MockHttpSession();
        AdminLoginInfo adminLoginInfo = new AdminLoginInfo();
        //白盒测试
        //1）	账号null
        System.out.println(administrationLoginController.login(null, session));
        //2）	账号密码正确
        System.out.println(administrationLoginController.login(new AdminLoginInfo("100001", "1234567"), session));
        //3）	账号密码错误
        System.out.println(administrationLoginController.login(new AdminLoginInfo("100223", "12312321"), session));
        //4）	账号密码长度错误
        System.out.println(administrationLoginController.login(new AdminLoginInfo("100223sdfsds", "12312321"), session));
    }
}
