package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Building;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminBuildingControllerTest {
    @Autowired
    private AdminBuildingController adminBuildingController;

    @Test
    void queryBuildingByName() {
        //1,正确楼名
        System.out.println(adminBuildingController.queryBuildingByName("致高楼"));
        //2,楼名null
        System.out.println(adminBuildingController.queryBuildingByName(null));
        //3,楼名长度0
        System.out.println(adminBuildingController.queryBuildingByName(""));
        //4楼名长度超过限制10
        System.out.println(adminBuildingController.queryBuildingByName("aaaaaaaaaaa"));
    }

    @Test
    void queryBuildingByBid() {
        //1,正确的楼id
        System.out.println(adminBuildingController.queryBuildingByBid(1));
        //2,错误的id
        System.out.println(adminBuildingController.queryBuildingByBid(12345));
    }

    @Test
    void queryBuildingByBidName() {
        //1,正确id楼名称
        System.out.println(adminBuildingController.queryBuildingByBidName(1, "致高楼"));
        //2,错误id,正确楼名称
        System.out.println(adminBuildingController.queryBuildingByBidName(1213, "致高楼"));
        //3,正确id,错误楼名称
        System.out.println(adminBuildingController.queryBuildingByBidName(1, "asdasdas"));
        //4,错误id,错误楼名称
        System.out.println(adminBuildingController.queryBuildingByBidName(1212321, "123213"));
    }

    @Test
    void updateBuilding() {
        Model model = new ConcurrentModel();
        model.addAttribute(Constants.ADMIN_SESSION, "100001");
        //1,正确building信息
        System.out.println(adminBuildingController.updateBuilding(model, new Building(1, "18350970000", "芙蓉楼", "东十")));
        //2,null,building信息
        System.out.println(adminBuildingController.updateBuilding(model, null));
        //3,空building信息
        System.out.println(adminBuildingController.updateBuilding(model, new Building()));
    }
}
