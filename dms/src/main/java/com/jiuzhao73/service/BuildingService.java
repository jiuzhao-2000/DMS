package com.jiuzhao73.service;

import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface BuildingService {
    Result<Building> queryBuildingByBid(int bid);

    Result<Building> queryBuildingByFullName(String name);

    Result<Building> queryBuildingByBidFullName(int bid, String name);

    Result<Boolean> updateBuilding(Building building);

    Result<Boolean> havePower(String aid, String buildingName);

    Result<Boolean> havePower(String aid, int bid);

    Result<List<Building>> queryBuildingListByName(String name, int pageNum, int pageSiz);

    Result<List<Building>> queryBuildingList(int pageNum, int pageSize);
}
