package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.service.MaintainerService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@SessionAttributes({Constants.MAINTAIN_SESSION})
@RequestMapping("/api/maintain/account")
public class MaintainerAccountController {
    private final MaintainerService maintainerService;

    public MaintainerAccountController(MaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }

    @Operation
    @ResponseBody
    @PostMapping("/update")
    public Result<Boolean> updateAccount(Model model,@Validated @RequestBody MaintainLoginInfo maintainLoginInfo) {
        Object mid = model.getAttribute(Constants.MAINTAIN_SESSION);
        if (!(mid instanceof Integer) || (Integer) mid != maintainLoginInfo.getMid()) {
            return Result.error("账号错误", false);
        }
        return maintainerService.updateAccount(maintainLoginInfo);
    }
}
