package com.jiuzhao73.controller;


import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.service.StudentService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Api(tags = "学生个人信息")
@SessionAttributes({Constants.STUDENT_SESSION})
@RestController
@RequestMapping("/api/student/self")
public class StudentSelfInfoController {
    private final StudentService studentService;

    public StudentSelfInfoController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(tags = "查询学生个人信息")
    @GetMapping("/query")
    @ResponseBody
    public StudentInfo queryStudentInfo(Model model) {
        Object sid = model.getAttribute(Constants.STUDENT_SESSION);
        if (sid instanceof String) {
            return studentService.getStudentInfo((String) sid);
        }
        return Constants.EMPTY_STUDENT_INFO;
    }

    @Operation(tags = "修改学生个人信息")
    @PostMapping("/update/{phone}")
    @ResponseBody
    public Result<Boolean> updateStudentInfo(Model model, @PathVariable String phone) {
        Object sid = model.getAttribute(Constants.STUDENT_SESSION);
        if (!(sid instanceof String) || phone == null || phone.isEmpty()) {
            return Result.error("sid或电话为空", false);
        }
        boolean matches = phone.matches("1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$");
        if (!matches){
            return Result.error("不是正确的手机号码", false);
        }
        return studentService.updateStudentPhone((String) sid, phone);
    }
}
