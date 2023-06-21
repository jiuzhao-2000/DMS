package com.jiuzhao73.controller;

import com.jiuzhao73.pojo.dto.RepairInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;

import java.util.ArrayList;

@SpringBootTest
class StudentDormControllerTest {
    @Autowired
    private StudentDormController studentDormController;

    @Test
    void createRepair() {
        RepairInfo info = new RepairInfo(0, 1, "台灯", "坏了。。。");
        ArrayList<RepairInfo> list = new ArrayList<>();
        list.add(info);
        System.out.println(studentDormController.createRepair(list));
    }
}
