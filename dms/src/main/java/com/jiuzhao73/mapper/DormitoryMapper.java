package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DormitoryMapper {
    int createDormitory(Dormitory d);

    int deleteDormitory(@Param("did") int did,@Param("bid") int bid);

    int updateDormitory(@Param("d")Dormitory d);

    List<Dormitory> queryDormitoryByBid(int bid);

    List<Dormitory> queryDormitoryByAid(String aid);

    Dormitory queryDormitory(@Param("did") int did,@Param("bid") int bid);

}
