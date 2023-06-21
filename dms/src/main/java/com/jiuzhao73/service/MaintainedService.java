package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.MaintainedInfo;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface MaintainedService {
    Result<List<MaintainedInfo>> queryMaintained(int mid, int pageNum, int pageSize);

    Result<Boolean> createMaintained(MaintainedInfo maintainedInfo);
}
