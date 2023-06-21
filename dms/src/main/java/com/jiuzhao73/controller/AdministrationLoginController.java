package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.service.AdminLoginService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(tags = "管理员登入接口")
@RestController
@SessionAttributes({Constants.ADMIN_SESSION})
@RequestMapping("/api/login")
public class AdministrationLoginController {
    private final AdminLoginService adminLoginService;

    @Operation(tags = "管理员登入")
    @PostMapping("/admin")
    @ResponseBody
    public Result<AdministratorInfo> login(@Validated @RequestBody AdminLoginInfo info, HttpSession httpSession) {
        if (info == null || AdminLoginInfo.isEmpty(info)) {
            return Result.error("登入信息不能为空", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        Result<AdministratorInfo> login = adminLoginService.login(info);
        if (Result.isSuccess(login)) {
            httpSession.setAttribute(Constants.ADMIN_SESSION, info.getAid());
        }
        return login;
    }

    public AdministrationLoginController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }
}
