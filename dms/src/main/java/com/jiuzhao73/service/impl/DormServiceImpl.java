package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.*;
import com.jiuzhao73.pojo.dto.DormitoryInfo;
import com.jiuzhao73.pojo.dto.RepairInfo;
import com.jiuzhao73.pojo.model.*;
import com.jiuzhao73.service.DormService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DormServiceImpl implements DormService {
    private final BuildingMapper buildingMapper;
    private final DormitoryMapper dormitoryMapper;
    private final StudentMapper studentMapper;

    public DormServiceImpl(BuildingMapper buildingMapper,
                           DormitoryMapper dormitoryMapper,
                           StudentMapper studentMapper) {
        this.buildingMapper = buildingMapper;
        this.dormitoryMapper = dormitoryMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public DormitoryInfo queryDormitoryList(String sid) {
        //检测sid
        if (sid == null || sid.isEmpty()) {
            return Constants.EMPTY_DORMITORY_INFO;
        }
        //该sid查询学生信息
        Student student = studentMapper.queryStudentBySid(sid);
        if (student == null) {
            return Constants.EMPTY_DORMITORY_INFO;
        }
        //查询该宿舍信息
        Dormitory dormitory = dormitoryMapper.queryDormitory(student.getDid(), student.getBid());
        if (dormitory == null) {
            return Constants.EMPTY_DORMITORY_INFO;
        }
        //查询宿舍楼信息
        Building building = buildingMapper.queryBuildingByBid(student.getBid());
        if (building == null) {
            return Constants.EMPTY_DORMITORY_INFO;
        }
        return DormitoryInfo.parseDormitoryInfo(dormitory, building.getName());
    }

    @Override
    public Result<List<DormitoryInfo>> queryDormListByFullBuildingName(String name,
                                                                       int pageNum,
                                                                       int pageSize) {
        if (name == null || name.isEmpty() || pageNum < 1 || pageSize < 1) {
            return Result.error("建筑名或页码出错", new ArrayList<>());
        }
        Building building = buildingMapper.queryBuildingListByFullName(name);
        if (building == null) {
            return Result.warning("没有查询到该楼信息", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Dormitory> dormitories = dormitoryMapper.queryDormitoryByBid(building.getBid());
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (dormitories == null || dormitories.size() == 0) {
            return Result.warning("没有查到宿舍信息", new ArrayList<>());
        }
        List<DormitoryInfo> dormitoryInfos = dormitories
                .stream()
                .map(d -> DormitoryInfo.parseDormitoryInfo(d, building.getName()))
                .collect(Collectors.toList());
        return Result.success("查询成功", dormitoryInfos, pageInfo);
    }

    @Override
    public Result<List<DormitoryInfo>> queryDormListByAid(String aid, int pageNum, int pageSize) {
        if (aid == null || aid.isEmpty() || pageNum < 1 || pageSize < 1) {
            return Result.error("管理员id或页码出错", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Dormitory> dormitories = dormitoryMapper.queryDormitoryByAid(aid);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (dormitories == null || dormitories.isEmpty()) {
            return Result.warning("管理的宿舍为空", new ArrayList<>());
        }
        List<DormitoryInfo> ans = dormitories.stream()
                .map(d -> DormitoryInfo.parseDormitoryInfo(d, buildingMapper.queryNameByBid(d.getBid())))
                .collect(Collectors.toList());
        return Result.success("查询成功", ans, pageInfo);
    }

}
