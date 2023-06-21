package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.mapper.BuildingMapper;
import com.jiuzhao73.mapper.StudentMapper;
import com.jiuzhao73.mapper.WealthMapper;
import com.jiuzhao73.pojo.dto.WealthInfo;
import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.pojo.model.Student;
import com.jiuzhao73.pojo.model.Wealth;
import com.jiuzhao73.service.WealthService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WealthServiceImpl implements WealthService {
    private final WealthMapper wealthMapper;
    private final StudentMapper studentMapper;
    private final BuildingMapper buildingMapper;

    public WealthServiceImpl(WealthMapper wealthMapper, StudentMapper studentMapper, BuildingMapper buildingMapper) {
        this.wealthMapper = wealthMapper;
        this.studentMapper = studentMapper;
        this.buildingMapper = buildingMapper;
    }

    @Override
    public List<WealthInfo> queryWealthInfo(String sid) {
        if (sid == null || sid.isEmpty()) {
            return new ArrayList<>();
        }
        Student student = studentMapper.queryStudentBySid(sid);
        Building building = buildingMapper.queryBuildingByBid(student.getBid());
        if (Building.isEmpty(building)) {
            return new ArrayList<>();
        }
        List<Wealth> wealth = wealthMapper.queryWealthListByBidDid(student.getBid(), student.getDid());
        if (wealth == null) {
            return new ArrayList<>();
        }
        return wealth
                .stream()
                .map(w -> WealthInfo.parseWealthInfo(w, building.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Result<List<WealthInfo>> queryWealthList(int bid, int did, int pageNum, int pageSize) {
        //合法性判断
        if (bid < 1 || did < 1 || pageNum < 1 || pageSize < 1) {
            return Result.error("楼号，宿舍号，页号，页面大小错误", new ArrayList<>());
        }
        //根据bid，did查询出List<Wealth>
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Wealth> wealth = wealthMapper.queryWealthListByBidDid(bid, did);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (wealth == null || wealth.size() == 0) {
            return Result.warning("没有查到财产信息，或该宿舍无财产信息", new ArrayList<>());
        }
        Building building = buildingMapper.queryBuildingByBid(bid);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("找不到宿舍楼信息", new ArrayList<>());
        }
        //List<Wealth>-->List<WealthInfo>
        List<WealthInfo> infos = wealth
                .stream()
                .map(w -> WealthInfo.parseWealthInfo(w, building.getName()))
                .collect(Collectors.toList());
        return Result.success("查询成功", infos, pageInfo);
    }

}
