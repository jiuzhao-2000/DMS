package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.pojo.dto.MaintainerInfo;
import com.jiuzhao73.util.Result;

public interface MaintainerService {
    MaintainerInfo queryMaintainerInfo(int mid);

    Result<Boolean> updateMaintainerNamePhone(MaintainerInfo info);

    Result<Boolean> updateAccount(MaintainLoginInfo maintainLoginInfo);
}
