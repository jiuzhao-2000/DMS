package com.jiuzhao73.service.impl;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.service.VisitorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;


@SpringBootTest
class VisitorServiceImplTest {
    @Autowired
    private VisitorService visitorService;

    @Test
    void queryVisitorListBySid() {
        Model model = new ConcurrentModel();
        model.addAttribute(Constants.STUDENT_SESSION, "1000000000");
        System.out.println(visitorService.queryVisitorListBySid("1000000000", 1, 20));
    }
}
