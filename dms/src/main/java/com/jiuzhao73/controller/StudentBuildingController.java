package com.jiuzhao73.controller;

import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.service.BuildingService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RequestMapping("/api/student/building")
@RestController
public class StudentBuildingController {
    private final BuildingService buildingService;

    public StudentBuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/show/{name}/{pageNum}/{pageSize}")
    public Result<List<Building>> getBuildingInfoByName(@PathVariable String name,
                                                        @PathVariable int pageNum,
                                                        @PathVariable int pageSize) {
        return buildingService.queryBuildingListByName(name, pageNum, pageSize);
    }

    @GetMapping("/show/{pageNum}/{pageSize}")
    public Result<List<Building>> getBuildingInfo(@PathVariable int pageNum,
                                                  @PathVariable int pageSize) {
        return buildingService.queryBuildingList(pageNum, pageSize);
    }
}
