package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.RepairInfo;
import com.jiuzhao73.pojo.dto.RepairListInfo;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface RepairService {

    Result<List<RepairListInfo>> queryRepairList(int pageNum, int pageSize);

    List<RepairInfo> queryRepair(int rid);

    Result<List<RepairInfo>> queryRepairInfoList(int pageNum, int pageSize);

    Result<List<RepairInfo>> queryRepairInfoList(int bid, int did, int pageNum, int pageSize);

    Result<Boolean> createRepairList(List<RepairInfo> repairInfo);

    Result<Boolean> updateRepair(RepairInfo info);
}
