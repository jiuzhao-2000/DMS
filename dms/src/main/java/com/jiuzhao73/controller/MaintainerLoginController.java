package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.pojo.dto.MaintainerInfo;
import com.jiuzhao73.service.MaintainLoginService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "维修员登入接口")
@RestController
@SessionAttributes({Constants.MAINTAIN_SESSION})
@RequestMapping("/api/login")
public class MaintainerLoginController {
    private final MaintainLoginService maintainLoginService;

    public MaintainerLoginController(MaintainLoginService maintainLoginService) {
        this.maintainLoginService = maintainLoginService;
    }

    @Operation(tags = "维修人员登入")
    @ResponseBody
    @PostMapping("/maintain")
    public Result<MaintainerInfo> login(@Valid @RequestBody MaintainLoginInfo info, HttpSession httpSession) {
        if (info == null || MaintainLoginInfo.isEmpty(info)) {
            return Result.error("登入信息不能为空", Constants.EMPTY_MAINTAINER_INFO);
        }
        Result<MaintainerInfo> result = maintainLoginService.login(info);
        if (Result.isSuccess(result)) {
            httpSession.setAttribute(Constants.MAINTAIN_SESSION, info.getMid());
        }
        return result;
    }

}
