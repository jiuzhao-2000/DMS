package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.service.AdminService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@SessionAttributes({Constants.ADMIN_SESSION})
@RequestMapping("/api/admin/account")
public class AdminAccountController {
    private final AdminService adminService;

    public AdminAccountController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation
    @PostMapping("/update")
    @ResponseBody
    public Result<Boolean> updateAccount(@Validated @RequestBody AdminLoginInfo info, Model model) {
        Object aid = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(aid instanceof String) || info == null || !info.getAid().equals(aid)) {
            return Result.warning("账号或密码为空", false);
        }
        return adminService.updateAdminAccount(info);
    }
}
