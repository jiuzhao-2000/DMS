package com.jiuzhao73.controller;

import com.jiuzhao73.pojo.dto.RepairInfo;
import com.jiuzhao73.pojo.dto.RepairListInfo;
import com.jiuzhao73.service.RepairService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/maintain/repair")
public class MaintainerRepairController {
    private final RepairService repairService;

    public MaintainerRepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/list/{pageNum}/{pageSize}")
    public Result<List<RepairListInfo>> queryRepairList(@PathVariable int pageNum, @PathVariable int pageSize) {
        return repairService.queryRepairList(pageNum, pageSize);
    }

    @Operation
    @ResponseBody
    @GetMapping("/show/{rid}")
    public List<RepairInfo> queryRepair(@PathVariable int rid) {
        return repairService.queryRepair(rid);
    }

}
