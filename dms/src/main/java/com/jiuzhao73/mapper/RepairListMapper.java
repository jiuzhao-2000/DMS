package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Repair;
import com.jiuzhao73.pojo.model.RepairList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface RepairListMapper {
    int createRepairList(RepairList r);

    int deleteRepairList(int rid);

    int updateRepairList(RepairList r);

    List<RepairList> queryRepairList();

    RepairList queryRepairListByRid(int rid);

    int queryMaxRid();
}
