package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Administration;
import com.jiuzhao73.pojo.model.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdministrationMapper {
    int createAdministration(Administration a);

    int deleteAdministration(@Param("aid") String aid, @Param("bid") int bid);

    int updateAdministration(Administration a);

    List<Administration> queryAdministrationListByAid(String aid);

    Administration queryAdministrationList(Administration a);

    List<Integer> queryBidListByAid(String aid);

}
