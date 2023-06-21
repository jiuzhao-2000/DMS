package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.mapper.MaintainedMapper;
import com.jiuzhao73.mapper.MaintainerMapper;
import com.jiuzhao73.mapper.WealthMapper;
import com.jiuzhao73.pojo.dto.MaintainedInfo;
import com.jiuzhao73.pojo.model.Maintained;
import com.jiuzhao73.pojo.model.Maintainer;
import com.jiuzhao73.pojo.model.Wealth;
import com.jiuzhao73.service.MaintainedService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintainedServiceImpl implements MaintainedService {
    private final MaintainedMapper maintainedMapper;
    private final MaintainerMapper maintainerMapper;
    private final WealthMapper wealthMapper;


    public MaintainedServiceImpl(MaintainedMapper maintainedMapper,
                                 WealthMapper wealthMapper,
                                 MaintainerMapper maintainerMapper) {
        this.maintainedMapper = maintainedMapper;
        this.wealthMapper = wealthMapper;
        this.maintainerMapper = maintainerMapper;
    }

    @Override
    public Result<List<MaintainedInfo>> queryMaintained(int mid, int pageNum, int pageSize) {
        if (mid < 1 || pageNum < 1 || pageSize < 1) {
            return Result.error("维修人员id或页码错误", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Maintained> maintained = maintainedMapper.queryMaintainedByMid(mid);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (maintained == null || maintained.isEmpty()) {
            return Result.warning("未找到维修记录", new ArrayList<>());
        }
        List<MaintainedInfo> infos = maintained.stream().map(m -> {
            Wealth wealth = wealthMapper.queryWealthByWid(m.getWid());
            return MaintainedInfo.toMaintainedInfo(m, wealth.getName());
        }).collect(Collectors.toList());

        return Result.success("查询成功", infos, pageInfo);
    }

    @Override
    public Result<Boolean> createMaintained(MaintainedInfo maintainedInfo) {
        if (maintainedInfo == null) {
            return Result.warning("创建信息为空", false);
        }
        //查询是否存在mid=该订单的maintainer
        Maintainer maintainer = maintainerMapper.queryMaintainerByMid(maintainedInfo.getMid());
        if (maintainer == null || maintainer.isEmpty()) {
            return Result.warning("未找到修改的信息", false);
        }
        Maintained maintained = MaintainedInfo.parseMaintained(maintainedInfo);
        if (maintainedMapper.createMaintained(maintained) == 1) {
            return Result.success("创建成功", true);
        }
        return Result.error("创建失败", false);
    }
}
