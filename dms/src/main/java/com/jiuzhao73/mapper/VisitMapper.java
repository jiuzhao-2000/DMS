package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VisitMapper {
    int createVisit(Visit v);

    int deleteVisit(String sid, String vid);

    int updateVisit(@Param("v") Visit v);

    List<Visit> queryVisit(String sid, String vid);

    List<Visit> queryVisitBySid(String sid);
}
