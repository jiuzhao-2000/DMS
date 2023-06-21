package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.AdministratorMapper;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminAccountControllerTest {
    @Autowired
    private AdminAccountController adminAccountController;
    @Autowired
    private AdministratorMapper administratorMapper;
    @Test
    void updateAccount() {
        Model model=new ConcurrentModel();
        model.addAttribute(Constants.ADMIN_SESSION,"100001");
        AdminLoginInfo adminLoginInfo=new AdminLoginInfo();
        System.out.println("现在100001账号的信息："+administratorMapper.queryAdministrator("100001"));
        //白盒测试
        //1）	账号null
        System.out.println("账号null:");
        System.out.println(adminAccountController.updateAccount(null, model));
        //2）	账号正确
        System.out.println("账号正确");
        System.out.println(adminAccountController.updateAccount(new AdminLoginInfo("100001","123456"), model));
        //3）	账号错误
        System.out.println("账号错误");
        System.out.println(adminAccountController.updateAccount(new AdminLoginInfo("100273","12312321"), model));
        //4）	账号密码长度错误
        System.out.println("账号密码长度错误");
        System.out.println(adminAccountController.updateAccount(new AdminLoginInfo("100223sdfsds","12312321"), model));
    }
}
