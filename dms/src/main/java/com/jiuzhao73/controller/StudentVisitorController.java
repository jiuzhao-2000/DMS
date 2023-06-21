package com.jiuzhao73.controller;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.VisitorInfo;
import com.jiuzhao73.service.VisitorService;
import com.jiuzhao73.util.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "学生访客信息")
@RestController
@SessionAttributes({Constants.STUDENT_SESSION})
@RequestMapping("/api/student")
public class StudentVisitorController {
    private final VisitorService visitorService;

    public StudentVisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Operation(tags = "查询访客")
    @GetMapping("/query/{pageNum}/{pageSize}")
    @ResponseBody
    public Result<List<VisitorInfo>> queryVisitorList(@PathVariable int pageNum,
                                                      @PathVariable int pageSize,
                                                      HttpSession session) {
        Object o = session.getAttribute(Constants.STUDENT_SESSION);
        if (!(o instanceof String)) {
            return Result.error("用户已被注销", new ArrayList<>());
        }
        return visitorService.queryVisitorListBySid((String) o, pageNum, pageSize);
    }

    @Operation(tags = "创建访客")
    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> createVisitorList(@RequestBody VisitorInfo info,
                                             Model model) {
        Object sid = model.getAttribute(Constants.STUDENT_SESSION);
        if (!(sid instanceof String)) {
            return Result.error("账号不存在", false);
        }
        return visitorService.createVisitor(info, (String) sid);
    }

    @Operation(tags = "修改访客记录")
    @PostMapping("/update")
    public Result<Boolean> updateVisitor(@RequestBody VisitorInfo info, Model model) {
        Object sid = model.getAttribute(Constants.STUDENT_SESSION);
        if (!(sid instanceof String)) {
            return Result.error("账号不存在", false);
        }
        return visitorService.updateVisitor(info, (String) sid);
    }
}
