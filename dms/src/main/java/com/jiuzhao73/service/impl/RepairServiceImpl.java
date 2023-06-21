package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.mapper.RepairListMapper;
import com.jiuzhao73.mapper.RepairMapper;
import com.jiuzhao73.mapper.WealthMapper;
import com.jiuzhao73.pojo.dto.RepairInfo;
import com.jiuzhao73.pojo.dto.RepairListInfo;
import com.jiuzhao73.pojo.model.Repair;
import com.jiuzhao73.pojo.model.RepairList;
import com.jiuzhao73.service.RepairService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairServiceImpl implements RepairService {
    private final RepairMapper repairMapper;
    private final RepairListMapper repairListMapper;
    private final WealthMapper wealthMapper;

    public RepairServiceImpl(RepairMapper repairMapper,
                             RepairListMapper repairListMapper,
                             WealthMapper wealthMapper) {
        this.repairMapper = repairMapper;
        this.repairListMapper = repairListMapper;
        this.wealthMapper = wealthMapper;
    }

    @Override
    public Result<List<RepairListInfo>> queryRepairList(int pageNum, int pageSize) {
        //合法性检测
        if (pageNum < 1 || pageSize < 1) {
            return Result.error("页码出错", new ArrayList<>());
        }
        //分页
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        //查询维修单
        List<RepairList> repairLists = repairListMapper.queryRepairList();
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (repairLists == null || repairLists.size() == 0) {
            return Result.warning("未查询到维修单信息", new ArrayList<>());
        }
        List<RepairListInfo> infos = repairLists
                .stream()
                .map(RepairListInfo::parseRepairListInfo)
                .collect(Collectors.toList());
        return Result.success("查询成功", infos, pageInfo);
    }

    @Override
    public List<RepairInfo> queryRepair(int rid) {
        List<Repair> repairs = repairMapper.queryRepairListByRid(rid);
        if (repairs == null || repairs.size() == 0) {
            return new ArrayList<>();
        }
        return repairs
                .stream()
                .map(
                        r -> RepairInfo.parseRepairInfo(r, wealthMapper.queryWealthByWid(r.getWid()).getName())
                )
                .collect(Collectors.toList()
                );
    }

    @Override
    public Result<List<RepairInfo>> queryRepairInfoList(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.error("页码错误", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Repair> repairs = repairMapper.queryRepairList();
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (repairs == null || repairs.isEmpty()) {
            return Result.warning("没有找到记录", new ArrayList<>());
        }
        List<RepairInfo> infos = repairs
                .stream()
                .map(r -> RepairInfo.parseRepairInfo(r, wealthMapper.queryWealthByWid(r.getWid()).getName()))
                .collect(Collectors.toList());
        return Result.success("查询成功", infos, pageInfo);
    }

    @Override
    public Result<List<RepairInfo>> queryRepairInfoList(int bid, int did, int pageNum, int pageSize) {
        //1、合法性检测
        if (bid < 1 || did < 1 || pageNum < 1 || pageSize < 1) {
            return Result.error("页码错误或者楼号错误", new ArrayList<>());
        }
        //获取维修列表
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Repair> repairs = repairMapper.queryRepairListByBidDid(bid, did);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (repairs == null || repairs.isEmpty()) {
            return Result.warning("没有查询到信息", new ArrayList<>());
        }
        //List<Repair>-->List<RepairInfo>
        List<RepairInfo> infos = repairs
                .stream()
                .map(r -> RepairInfo.parseRepairInfo(r, wealthMapper.queryWealthByWid(r.getWid()).getName()))
                .collect(Collectors.toList());
        return Result.success("查询成功", infos, pageInfo);
    }

    @Override
    @Transactional
    public Result<Boolean> createRepairList(List<RepairInfo> infos) {
        //1、合法性检测
        if (infos == null || infos.isEmpty()) {
            return Result.warning("没有需要维修的物品", true);
        }
        //2、获取rid
        int rid = repairListMapper.queryMaxRid() + 1;
        //2、创建维修单 repairList
        RepairList repairList = new RepairList(rid, new Date(System.currentTimeMillis()));
        int insert = repairListMapper.createRepairList(repairList);
        if (insert != 1) {
            return Result.error("创建维修单失败", false);
        }
        //3、创建维修列表 RepairInfo->Repair 并创建
        infos.stream()
                .map(RepairInfo::toRepair)
                .forEach(r -> {
                    r.setRid(rid);
                    repairMapper.createRepair(r);
                });
        return Result.success("创建成功", true);
    }

    @Override
    public Result<Boolean> updateRepair(RepairInfo info) {
        if (info == null || RepairInfo.isEmpty(info)) {
            return Result.warning("没有要修改的信息", false);
        }
        int i = repairMapper.updateRepair(RepairInfo.toRepair(info));
        if (i == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }

}
