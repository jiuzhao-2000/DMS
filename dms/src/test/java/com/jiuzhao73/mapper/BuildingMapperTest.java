package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Building;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuildingMapperTest {
    @Autowired
    private BuildingMapper buildingMapper;

    @Test
    void createBuilding() {
        Building building = new Building(11, "18350999999", "jiuzhao", "西10");
        System.out.println(buildingMapper.createBuilding(building));
    }

    @Test
    void deleteBuilding() {
        System.out.println(buildingMapper.deleteBuilding(11));
    }

    @Test
    void updateBuilding() {
        Building building = new Building(11, "18350999988", "", "西100");
        System.out.println(buildingMapper.updateBuilding(building));
    }

    @Test
    void queryBuildingList() {
        System.out.println(buildingMapper.queryBuildingList());
    }

    @Test
    void queryBuildingListByName() {
        System.out.println(buildingMapper.queryBuildingListByName("高"));
    }

}
