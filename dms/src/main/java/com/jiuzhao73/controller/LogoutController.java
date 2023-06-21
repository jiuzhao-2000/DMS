package com.jiuzhao73.controller;

import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

@Api(tags = "登出接口")
@Controller
@RequestMapping("/api/logout")
public class LogoutController {
    @Operation(tags = "学生登出")
    @GetMapping("/student")
    @ResponseBody
    public Result<Boolean> studentLogout(SessionStatus status) {
        status.setComplete();
        return Result.success("成功退出",true);
    }

    @Operation(tags = "管理员登出")
    @GetMapping("/admin")
    @ResponseBody
    public Result<Boolean> adminLogout(SessionStatus status) {
        status.setComplete();
        return Result.success("成功退出",true);
    }

    @Operation(tags = "维修员登出")
    @GetMapping("/maintain")
    @ResponseBody
    public Result<Boolean> maintainLogout(SessionStatus status) {
        status.setComplete();
        return Result.success("成功退出",true);
    }
}
