package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.DormitoryInfo;
import com.jiuzhao73.pojo.dto.RepairInfo;
import com.jiuzhao73.pojo.dto.WealthInfo;
import com.jiuzhao73.service.DormService;
import com.jiuzhao73.service.RepairService;
import com.jiuzhao73.service.WealthService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@SessionAttributes({Constants.STUDENT_SESSION})
@RequestMapping("/api/student/dorm")
public class StudentDormController {
    private final DormService dormService;
    private final WealthService wealthService;
    private final RepairService repairService;

    public StudentDormController(DormService dormService,
                                 WealthService wealthService,
                                 RepairService repairService) {
        this.dormService = dormService;
        this.wealthService = wealthService;
        this.repairService = repairService;
    }

    @Operation
    @GetMapping("/show")
    @ResponseBody
    public DormitoryInfo queryDorm(Model model) {
        Object o = model.getAttribute(Constants.STUDENT_SESSION);
        if (o instanceof String) {
            return dormService.queryDormitoryList((String) o);
        }
        return Constants.EMPTY_DORMITORY_INFO;
    }

    @Operation
    @GetMapping("/wealth")
    @ResponseBody
    public List<WealthInfo> queryDormWealth(Model model) {
        Object o = model.getAttribute(Constants.STUDENT_SESSION);
        if (o instanceof String) {
            return wealthService.queryWealthInfo((String) o);
        }
        return new ArrayList<>();
    }

    @Operation
    @PostMapping("/repair/add")
    @ResponseBody
    public Result<Boolean> createRepair(@RequestBody List<RepairInfo> infos) {
        System.out.println(infos);
        if (infos.isEmpty() || infos.contains(Constants.EMPTY_REPAIR_INFO)) {
            return Result.error("维修单不能为空", false);
        }
        return repairService.createRepairList(infos);
    }
}
