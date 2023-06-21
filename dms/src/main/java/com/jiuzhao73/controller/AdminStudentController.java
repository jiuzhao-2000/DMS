package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.service.AdminLoginService;
import com.jiuzhao73.service.StudentService;
import com.jiuzhao73.service.BuildingService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@SessionAttributes({Constants.ADMIN_SESSION})
@RequestMapping("/api/admin/stu")
public class AdminStudentController {
    private final StudentService studentService;
    private final BuildingService buildingService;
    private final AdminLoginService adminLoginService;

    public AdminStudentController(StudentService studentService,
                                  BuildingService buildingService,
                                  AdminLoginService adminLoginService) {
        this.studentService = studentService;
        this.buildingService = buildingService;
        this.adminLoginService = adminLoginService;
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/{pageNum}/{pageSize}")
    public Result<List<StudentInfo>> queryStudentList(@PathVariable int pageNum,
                                                      @PathVariable int pageSize) {
        return studentService.queryStudentList(pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/sName/{name}/{pageNum}/{pageSize}")
    public Result<List<StudentInfo>> queryStudentListByName(@PathVariable String name,
                                                            @PathVariable int pageNum,
                                                            @PathVariable int pageSize) {
        if (name == null || name.isEmpty()) {
            return queryStudentList(pageNum, pageSize);
        }
        return studentService.queryStudentByName(name, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/bName/{buildingName}/{pageNum}/{pageSize}")
    public Result<List<StudentInfo>> queryStudentListByBuildingName(@PathVariable String buildingName,
                                                                    @PathVariable int pageNum,
                                                                    @PathVariable int pageSize) {
        if (buildingName == null || buildingName.isEmpty()) {
            return queryStudentList(pageNum, pageSize);
        }
        return studentService.queryStudentListByBuildingName(buildingName, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/sid/{sid}")
    public Result<StudentInfo> queryStudentListBySid(@PathVariable String sid) {
        if (sid == null || sid.isEmpty()) {
            return Result.error("传入参数不能为空",Constants.EMPTY_STUDENT_INFO);
        }
        return studentService.queryStudentBySid(sid);
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/sidName/{sid}/{name}/{pageNum}/{pageSize}")
    public Result<List<StudentInfo>> queryStudentBySidName(@PathVariable String sid,
                                                           @PathVariable String name,
                                                           @PathVariable int pageNum,
                                                           @PathVariable int pageSize) {
        if (sid == null || sid.isEmpty() || name == null || name.isEmpty()) {
            return Result.error("",new ArrayList<>());
        }
        return studentService.queryStudentBySidName(sid, name,pageNum,pageSize);
    }

    @Operation
    @ResponseBody
    @PostMapping("/alter/{aid}/{pwd}")
    public Result<Boolean> updateStudent(Model model,
                                         @RequestBody StudentInfo studentInfo,
                                         @PathVariable String aid,
                                         @PathVariable String pwd) {
        Object admin = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(admin instanceof String) || ((String) admin).isEmpty() || aid.isEmpty() || pwd.isEmpty()) {
            return Result.warning("账号或者密码错误", false);
        }
        AdminLoginInfo adminLoginInfo = new AdminLoginInfo(aid, pwd);
        Result<AdministratorInfo> login = adminLoginService.login(adminLoginInfo);
        if (!Result.isSuccess(login)) {
            return Result.warning("账号或者密码错误", false);
        }
        if (studentInfo == null || StudentInfo.isEmpty(studentInfo)) {
            return Result.warning("修改的学生信息不能为空", false);
        }
        //判断自己是否有修改这个学生的权限
        if (!buildingService.havePower(aid, studentInfo.getBuildingName()).getData()) {
            return Result.error("无修改该学生的权限", false);
        }
        return studentService.updateStudent(studentInfo);
    }
}
