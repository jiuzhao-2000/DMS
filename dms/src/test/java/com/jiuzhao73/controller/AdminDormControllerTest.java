package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminDormControllerTest {
    @Autowired
    private AdminDormController adminDormController;

    @Test
    void queryDormByBuildingName() {
        Model model = new ConcurrentModel();
        model.addAttribute(Constants.ADMIN_SESSION, "100001");
        //1,正确的楼名
        System.out.println(adminDormController.queryDormByBuildingName(model, "致高楼", 1, 10));
        //2,错误楼名
        System.out.println(adminDormController.queryDormByBuildingName(model, "asda", 1, 10));
        System.out.println(adminDormController.queryDormByBuildingName(model, null, 1, 10));
        //3,错误页码
        System.out.println(adminDormController.queryDormByBuildingName(model, "致高楼", -1, -10));
    }

    @Test
    void queryDormByAid() {

    }

    @Test
    void queryWealth() {
    }

    @Test
    void queryRepairList() {
    }

    @Test
    void testQueryRepairList() {
    }

    @Test
    void createRepairList() {
    }

    @Test
    void updateRepairList() {
    }
}
