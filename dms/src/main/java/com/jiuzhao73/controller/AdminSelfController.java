package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.service.AdminLoginService;
import com.jiuzhao73.service.AdminService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@RestController
@SessionAttributes({Constants.ADMIN_SESSION})
@RequestMapping("/api/admin/self")
public class AdminSelfController {
    private final AdminService adminService;
    private final AdminLoginService adminLoginService;

    public AdminSelfController(AdminService adminService,
                               AdminLoginService adminLoginService) {
        this.adminService = adminService;
        this.adminLoginService = adminLoginService;
    }

    @Operation
    @ResponseBody
    @GetMapping("/show")
    public Result<AdministratorInfo> queryAdministratorInfo(Model model) {
        Object o = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(o instanceof String)) {
            return Result.error("尚未登入", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        return adminService.queryAdminByAid((String) o);
    }

    @Operation
    @ResponseBody
    @PostMapping("/alter")
    public Result<Boolean> updateAdministrator(Model model,
                                               @RequestBody AdministratorInfo administratorInfo) {
        Object o = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(o instanceof String) || !administratorInfo.getAid().equals(o)) {
            return Result.warning("修改信息为空", false);
        }
        return adminService.updateAdmin(administratorInfo);
    }

    @Operation(tags = "验证密码")
    @ResponseBody
    @PostMapping("/isTrue")
    public Result<AdministratorInfo> isTruePwd(Model model, @Valid @RequestBody AdminLoginInfo info) {
        if (info == null || AdminLoginInfo.isEmpty(info) || info.getPwd().isEmpty()) {
            return Result.error("密码不能为空", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        Object o = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(o instanceof String) || !info.getAid().equals(o)) {
            return Result.error("aid不相同", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        return adminLoginService.login(info);
    }


}
