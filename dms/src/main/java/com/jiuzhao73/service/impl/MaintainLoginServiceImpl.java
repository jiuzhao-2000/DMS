package com.jiuzhao73.service.impl;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.MaintainedMapper;
import com.jiuzhao73.mapper.MaintainerMapper;
import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.pojo.dto.MaintainerInfo;
import com.jiuzhao73.pojo.model.Maintainer;
import com.jiuzhao73.service.MaintainLoginService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

@Service
public class MaintainLoginServiceImpl implements MaintainLoginService {
    private final MaintainerMapper maintainerMapper;
    private final MaintainedMapper maintainedMapper;

    @Override
    public Result<MaintainerInfo> login(MaintainLoginInfo info) {
        if (info == null || MaintainLoginInfo.isEmpty(info)) {
            return Result.error("登入信息为空", Constants.EMPTY_MAINTAINER_INFO);
        }
        Maintainer maintainer = Maintainer.parseMaintainer(info);
        if (maintainerMapper.queryMaintainerByMidPwd(maintainer) > 0) {
            int num = maintainedMapper.queryWorkNumByMid(maintainer.getMid());
            maintainer = maintainerMapper.queryMaintainerByMid(maintainer.getMid());
            MaintainerInfo maintainerInfo = MaintainerInfo.toMaintainerInfo(maintainer, num);
            return Result.success("登入成功", maintainerInfo);
        }
        return Result.warning("账号或密码错误", Constants.EMPTY_MAINTAINER_INFO);
    }

    public MaintainLoginServiceImpl(MaintainerMapper maintainerMapper, MaintainedMapper maintainedMapper) {
        this.maintainerMapper = maintainerMapper;
        this.maintainedMapper = maintainedMapper;
    }
}
