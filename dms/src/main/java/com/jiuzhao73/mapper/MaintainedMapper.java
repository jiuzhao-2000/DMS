package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Maintained;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MaintainedMapper {
    int createMaintained(Maintained m);

    int deleteMaintained(int mid, int wid);

    int deleteMaintainedByWid(int wid);

    int updateMaintained(Maintained m);

    List<Maintained> queryMaintained();

    List<Maintained> queryMaintainedByMid(int mid);

    List<Maintained> queryMaintainedByWid(int wid);

    Maintained queryMaintainedByMidAndWid(int mid, int wid);

    int queryWorkNumByMid(int mid);
}
