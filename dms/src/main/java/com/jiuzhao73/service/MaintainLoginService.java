package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.pojo.dto.MaintainerInfo;
import com.jiuzhao73.util.Result;

public interface MaintainLoginService {
    Result<MaintainerInfo> login(MaintainLoginInfo info);
}
