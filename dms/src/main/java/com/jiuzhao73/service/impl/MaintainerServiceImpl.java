package com.jiuzhao73.service.impl;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.MaintainedMapper;
import com.jiuzhao73.mapper.MaintainerMapper;
import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.pojo.dto.MaintainerInfo;
import com.jiuzhao73.pojo.model.Maintainer;
import com.jiuzhao73.service.MaintainerService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

@Service
public class MaintainerServiceImpl implements MaintainerService {
    private final MaintainerMapper maintainerMapper;
    private final MaintainedMapper maintainedMapper;

    public MaintainerServiceImpl(MaintainerMapper maintainerMapper, MaintainedMapper maintainedMapper) {
        this.maintainerMapper = maintainerMapper;
        this.maintainedMapper = maintainedMapper;
    }

    @Override
    public MaintainerInfo queryMaintainerInfo(int mid) {
        if (mid < 1) {
            return Constants.EMPTY_MAINTAINER_INFO;
        }
        Maintainer maintainer = maintainerMapper.queryMaintainerByMid(mid);
        if (maintainer == null) {
            return Constants.EMPTY_MAINTAINER_INFO;
        }
        int workNum = maintainedMapper.queryWorkNumByMid(mid);
        return MaintainerInfo.toMaintainerInfo(maintainer, workNum);
    }

    @Override
    public Result<Boolean> updateMaintainerNamePhone(MaintainerInfo info) {
        if (info == null || MaintainerInfo.isEmpty(info)) {
            return Result.warning("修改信息为空", false);
        }
        Maintainer maintainer = MaintainerInfo.parseMaintainer(info, "");
        if (maintainer == null || maintainer.isEmpty()) {
            return Result.warning("维修人员信息为空", false);
        }
        if (maintainerMapper.updateMaintainer(maintainer) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }

    @Override
    public Result<Boolean> updateAccount(MaintainLoginInfo maintainLoginInfo) {
        if (maintainLoginInfo == null || MaintainLoginInfo.isEmpty(maintainLoginInfo)) {
            return Result.warning("修改的账号信息为空", false);
        }
        Maintainer maintainer = Maintainer.parseMaintainer(maintainLoginInfo);
        int account = maintainerMapper.queryMaintainerByMidPwd(maintainer);
        //修改密码与现密码相同
        if (account != 1) {
            return Result.warning("修改的密码不能和原来的一样", false);
        }
        if (maintainerMapper.updateMaintainerPwd(maintainer.getMid(), maintainer.getPwd()) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }

}
