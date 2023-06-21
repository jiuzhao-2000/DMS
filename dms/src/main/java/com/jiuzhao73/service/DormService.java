package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.DormitoryInfo;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface DormService {
    DormitoryInfo queryDormitoryList(String sid);

    Result<List<DormitoryInfo>> queryDormListByFullBuildingName(String name, int pageNum, int pageSize);

    Result<List<DormitoryInfo>> queryDormListByAid(String aid, int pageNum, int pageSize);

}
