package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.MaintainedInfo;
import com.jiuzhao73.service.MaintainedService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@SessionAttributes({Constants.MAINTAIN_SESSION})
@RequestMapping("/api/maintain/maintained")
public class MaintainerMaintainedController {
    private final MaintainedService maintainedService;

    public MaintainerMaintainedController(MaintainedService maintainedService) {
        this.maintainedService = maintainedService;
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/{pageNum}/{pageSize}")
    public Result<List<MaintainedInfo>> queryMaintainedInfoList(Model model,
                                                                @PathVariable int pageNum,
                                                                @PathVariable int pageSize) {
        Object o = model.getAttribute(Constants.MAINTAIN_SESSION);
        if (o == null) {
            return Result.error("没有找到mid",new ArrayList<>());
        }
        return maintainedService.queryMaintained((int) o, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @PostMapping("/add")
    public Result<Boolean> createMaintained(@Validated @RequestBody MaintainedInfo maintainedInfo) {
        return maintainedService.createMaintained(maintainedInfo);
    }
}
