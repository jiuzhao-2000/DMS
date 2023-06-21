package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.service.BuildingService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("管理员宿舍楼管理")
@RestController
@SessionAttributes({Constants.ADMIN_SESSION})
@RequestMapping("/api/admin/building")
public class AdminBuildingController {
    private final BuildingService buildingService;

    public AdminBuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Operation(tags = "根据宿舍楼名查询宿舍楼信息")
    @ResponseBody
    @GetMapping("/showByName/{name}")
    public Result<Building> queryBuildingByName(@PathVariable String name) {
        return buildingService.queryBuildingByFullName(name);
    }

    @Operation(tags = "根据宿舍楼id查询宿舍楼信息")
    @ResponseBody
    @GetMapping("/showByBid/{bid}")
    public Result<Building> queryBuildingByBid(@PathVariable int bid) {
        return buildingService.queryBuildingByBid(bid);
    }

    @Operation(tags = "根据宿舍楼id宿舍楼名查询宿舍楼信息")
    @ResponseBody
    @GetMapping("/showByBidName/{bid}/{name}")
    public Result<Building> queryBuildingByBidName(@PathVariable int bid, @PathVariable String name) {
        return buildingService.queryBuildingByBidFullName(bid, name);
    }

    @Operation(tags = "修改宿舍楼信息")
    @ResponseBody
    @PostMapping("/alter")
    public Result<Boolean> updateBuilding(Model model, @Validated Building building) {
        Object o = model.getAttribute(Constants.ADMIN_SESSION);
        if (!(o instanceof String) || building == null || Building.isEmpty(building)) {
            return Result.error("传入空值", false);
        }
        //判断该管理员是否有权限修改该楼
        Result<Boolean> result = buildingService.havePower((String) o, building.getBid());
        if (!result.getData()) {
            return result;
        }
        return buildingService.updateBuilding(building);
    }

}
