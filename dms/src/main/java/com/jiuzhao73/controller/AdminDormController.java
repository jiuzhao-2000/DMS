package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.DormitoryInfo;
import com.jiuzhao73.pojo.dto.RepairInfo;
import com.jiuzhao73.pojo.dto.WealthInfo;
import com.jiuzhao73.service.BuildingService;
import com.jiuzhao73.service.DormService;
import com.jiuzhao73.service.RepairService;
import com.jiuzhao73.service.WealthService;
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
@SessionAttributes({Constants.ADMIN_SESSION})
@RequestMapping("/api/admin/dorm")
public class AdminDormController {
    private final DormService dormService;
    private final WealthService wealthService;
    private final RepairService repairService;
    private final BuildingService buildingService;

    public AdminDormController(DormService dormService,
                               WealthService wealthService,
                               RepairService repairService,
                               BuildingService buildingService) {
        this.dormService = dormService;
        this.wealthService = wealthService;
        this.repairService = repairService;
        this.buildingService = buildingService;
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/{buildingName}/{pageNum}/{pageSize}")
    public Result<List<DormitoryInfo>> queryDormByBuildingName(Model model,
                                                               @PathVariable String buildingName,
                                                               @PathVariable int pageNum,
                                                               @PathVariable int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.warning("页面错误", new ArrayList<>());
        }
        Object o = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(o instanceof String)) {
            return Result.error("id不存在", new ArrayList<>());
        }
        Result<Boolean> result = buildingService.havePower((String) o, buildingName);
        if (!Result.isSuccess(result)) {
            return Result.warning(result.getMessage(), new ArrayList<>());
        }
        return dormService.queryDormListByFullBuildingName(buildingName, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/{pageNum}/{pageSize}")
    public Result<List<DormitoryInfo>> queryDormByAid(Model model,
                                                      @PathVariable int pageNum,
                                                      @PathVariable int pageSize) {
        Object o = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(o instanceof String) || pageNum < 1 || pageSize < 1) {
            return Result.warning("id不存在或页码错误", new ArrayList<>());
        }
        return dormService.queryDormListByAid((String) o, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/wealth/{bid}/{did}/{pageNum}/{pageSize}")
    public Result<List<WealthInfo>> queryWealth(@PathVariable int bid,
                                                @PathVariable int did,
                                                @PathVariable int pageNum,
                                                @PathVariable int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.warning("页面错误", new ArrayList<>());
        }
        if (bid < 0 || did < 0) {
            return Result.warning("宿舍号错误", new ArrayList<>());
        }
        return wealthService.queryWealthList(bid, did, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/repair/{pageNum}/{pageSize}")
    public Result<List<RepairInfo>> queryRepairList(@PathVariable int pageNum,
                                                    @PathVariable int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.warning("页面错误", new ArrayList<>());
        }
        return repairService.queryRepairInfoList(pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/repairBid/{bid}/{did}/{pageNum}/{pageSize}")
    public Result<List<RepairInfo>> queryRepairList(@PathVariable int bid,
                                                    @PathVariable int did,
                                                    @PathVariable int pageNum,
                                                    @PathVariable int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.warning("页面错误", new ArrayList<>());
        }
        if (bid < 0 || did < 0) {
            return Result.warning("宿舍号错误", new ArrayList<>());
        }
        return repairService.queryRepairInfoList(bid, did, pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/repair/add")
    public Result<Boolean> createRepairList(@Validated List<RepairInfo> infos) {
        if (infos.isEmpty()) {
            return Result.warning("传入信息不能为空", false);
        }
        return repairService.createRepairList(infos);
    }

    @Operation
    @ResponseBody
    @PostMapping("/repair/alter")
    public Result<Boolean> updateRepairList(@Validated RepairInfo info) {
        if (RepairInfo.isEmpty(info)) {
            return Result.warning("传入信息不能为空", false);
        }
        return repairService.updateRepair(info);
    }
}
