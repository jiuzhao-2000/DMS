package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.service.StudentLoginService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "学生登入接口")
@RestController
@SessionAttributes({Constants.STUDENT_SESSION})
@RequestMapping("/api/login")
public class StudentLoginController {
    private final StudentLoginService studentLoginService;

    @Operation(tags = "学生登入")
    @PostMapping("/student")
    @ResponseBody
    public StudentInfo login(@Valid @RequestBody StudentLoginInfo info, HttpSession session) {
        if (info == null || StudentLoginInfo.isEmpty(info)) {
            return Constants.EMPTY_STUDENT_INFO;
        }
        StudentInfo login = studentLoginService.login(info);
        if (login != null && !StudentInfo.isEmpty(login)) {
            session.setAttribute(Constants.STUDENT_SESSION, info.getSid());
        }
        return login;
    }

    public StudentLoginController(StudentLoginService studentLoginService) {
        this.studentLoginService = studentLoginService;
    }
}
