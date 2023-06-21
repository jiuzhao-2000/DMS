package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.service.StudentService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api
@RestController
@SessionAttributes({Constants.STUDENT_SESSION})
@RequestMapping("/api/student/account")
public class StudentAccountController {
    private final StudentService studentService;

    public StudentAccountController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation
    @PostMapping("/update")
    @ResponseBody
    public Result<Boolean> updateAccount(@Validated @RequestBody StudentLoginInfo info,
                                         Model model) {
        Object sid = model.getAttribute(Constants.STUDENT_SESSION);
        if (!(sid instanceof String) || StudentLoginInfo.isEmpty(info)) {
            return Result.error("登入信息不能为空", false);
        }
        return studentService.updateStudent(info);
    }
}
