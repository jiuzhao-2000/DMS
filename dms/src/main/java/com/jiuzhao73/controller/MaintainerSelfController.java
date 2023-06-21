package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.pojo.dto.MaintainerInfo;
import com.jiuzhao73.service.MaintainLoginService;
import com.jiuzhao73.service.MaintainerService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Api
@RestController
@SessionAttributes({Constants.MAINTAIN_SESSION})
@RequestMapping("/api/maintain/self")
public class MaintainerSelfController {
    private final MaintainerService maintainerService;
    private final MaintainLoginService maintainLoginService;

    public MaintainerSelfController(MaintainerService maintainerService,
                                    MaintainLoginService maintainLoginService) {
        this.maintainerService = maintainerService;
        this.maintainLoginService = maintainLoginService;
    }

    @Operation
    @ResponseBody
    @GetMapping("/show")
    public MaintainerInfo queryMaintainerSelfInfo(Model model) {
        Object mid = model.getAttribute(Constants.MAINTAIN_SESSION);
        if (mid == null) {
            return Constants.EMPTY_MAINTAINER_INFO;
        }
        return maintainerService.queryMaintainerInfo((int) mid);
    }

    @Operation
    @ResponseBody
    @PostMapping("/alter")
    public Result<Boolean> updateMaintainer(Model model,@Validated @RequestBody MaintainerInfo info) {
        Object mid = model.getAttribute(Constants.MAINTAIN_SESSION);
        if (!(mid instanceof Integer) || (Integer) mid != info.getMid()) {
            return Result.warning("修改信息不能为空", false);
        }
        return maintainerService.updateMaintainerNamePhone(info);
    }

    @Operation(tags = "验证密码")
    @ResponseBody
    @PostMapping("/isTrue")
    public Result<MaintainerInfo> isTruePwd(@Valid @RequestBody MaintainLoginInfo info, HttpSession httpSession) {
        if (info == null || MaintainLoginInfo.isEmpty(info) || info.getPwd().isEmpty()) {
            return Result.error("密码不能为空", Constants.EMPTY_MAINTAINER_INFO);
        }
        return maintainLoginService.login(info);
    }
}
