package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VisitorMapper {

    int createVisitor(Visitor v);

    int deleteVisitor(String vid);

    int updateVisitor(@Param("v") Visitor v);

    List<Visitor> queryVisitor(@Param("v") Visitor v);

    Visitor queryVisitorByVid(String vid);

    int queryVisitorNumByVid(String vid);

}
