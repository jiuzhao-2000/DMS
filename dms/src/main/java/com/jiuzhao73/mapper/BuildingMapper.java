package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BuildingMapper {
    int createBuilding(Building b);

    int deleteBuilding(int bid);

    int updateBuilding(@Param("b") Building b);

    List<Building> queryBuildingList();

    List<Building> queryBuildingListByName(String name);

    Building queryBuildingListByFullName(String name);

    Building queryBuildingByBid(int bid);

    Building queryBuildingByBidFullName(int bid, String name);

    String queryNameByBid(int bid);

    List<Building> queryBuildingListByAid(String aid);

    List<String> queryBuildingNameListByAid(String aid);
}
