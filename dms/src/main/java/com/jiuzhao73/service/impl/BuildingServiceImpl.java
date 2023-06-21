package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.AdministrationMapper;
import com.jiuzhao73.mapper.BuildingMapper;
import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.service.BuildingService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingMapper buildingMapper;
    private final AdministrationMapper administrationMapper;

    public BuildingServiceImpl(BuildingMapper buildingMapper,
                               AdministrationMapper administrationMapper) {
        this.buildingMapper = buildingMapper;
        this.administrationMapper = administrationMapper;
    }

    @Override
    public Result<Building> queryBuildingByBid(int bid) {
        if (bid < 1) {
            return Result.warning("bid错误", Constants.EMPTY_BUILDING);
        }
        Building building = buildingMapper.queryBuildingByBid(bid);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("没有找到", Constants.EMPTY_BUILDING);
        }
        return Result.success("查询成功", building);
    }

    @Override
    public Result<Building> queryBuildingByFullName(String name) {
        if (name == null || name.isEmpty()) {
            return Result.warning("楼名不能为空", Constants.EMPTY_BUILDING);
        }
        Building building = buildingMapper.queryBuildingListByFullName(name);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("没有查到", Constants.EMPTY_BUILDING);
        }
        return Result.success("查询成功", building);
    }

    @Override
    public Result<Building> queryBuildingByBidFullName(int bid, String name) {
        if (bid < 1 || name == null || name.isEmpty()) {
            return Result.warning("查询条件错误", Constants.EMPTY_BUILDING);
        }
        Building building = buildingMapper.queryBuildingByBidFullName(bid, name);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("没有查到", Constants.EMPTY_BUILDING);
        }
        return Result.success("查询成功", building);
    }

    @Override
    public Result<Boolean> updateBuilding(Building building) {
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("修改信息为空", false);
        }
        if (buildingMapper.updateBuilding(building) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }

    @Override
    public Result<Boolean> havePower(String aid, String buildingName) {
        if (aid == null || aid.isEmpty() || buildingName == null || buildingName.isEmpty()) {
            return Result.error("传递空值", false);
        }
        List<Integer> integers = administrationMapper.queryBidListByAid(aid);
        if (integers == null) {
            return Result.warning("查询权限失败", false);
        }
        Building building = buildingMapper.queryBuildingListByFullName(buildingName);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("未找到宿舍楼", false);
        }
        if (integers.contains(building.getBid())) {
            return Result.success("有权限", true);
        }
        return Result.warning("没有权限", false);
    }

    @Override
    public Result<Boolean> havePower(String aid, int bid) {
        if (aid == null || aid.isEmpty() || bid < 0) {
            return Result.error("传递空值", false);
        }
        List<Integer> integers = administrationMapper.queryBidListByAid(aid);
        if (integers == null) {
            return Result.warning("查询权限失败", false);
        }
        Building building = buildingMapper.queryBuildingByBid(bid);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("未找到宿舍楼", false);
        }
        if (integers.contains(building.getBid())) {
            return Result.success("有权限", true);
        }
        return Result.warning("没有权限", false);
    }

    @Override
    public Result<List<Building>> queryBuildingListByName(String name, int pageNum, int pageSize) {
        if (name == null || name.isEmpty()) {
            return Result.error("查询名称不能为空", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Building> buildings = buildingMapper.queryBuildingListByName(name);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (buildings == null || buildings.isEmpty()) {
            return Result.warning("没有查询到名字与" + name + "相关的宿舍楼", new ArrayList<>());
        }
        return Result.success("查询到名字与" + name + "相关的信息", buildings, pageInfo);
    }

    @Override
    public Result<List<Building>> queryBuildingList(int pageNum, int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Building> buildings = buildingMapper.queryBuildingList();
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (buildings == null || buildings.isEmpty()) {
            return Result.warning("没有查询宿舍楼", new ArrayList<>());
        }
        return Result.success("查询信息", buildings, pageInfo);
    }
}
