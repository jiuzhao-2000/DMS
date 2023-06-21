package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RepairMapper {
    int createRepair(Repair r);

    int deleteRepair(int rid, int wid);

    int deleteRepairByRid(int rid);

    int deleteRepairByWid(int wid);

    int updateRepair(Repair r);

    Repair queryRepair(int rid, int wid);

    List<Repair> queryRepairListByRid(int rid);

    int queryWidByRid(int rid);

    List<Repair> queryRepairList();

    List<Repair> queryRepairListByBidDid(int bid,int did);

}
