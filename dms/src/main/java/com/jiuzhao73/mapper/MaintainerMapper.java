package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Maintainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MaintainerMapper {
    int createMaintainer(Maintainer m);

    int deleteMaintainer(int mid);

    int updateMaintainer(@Param("m") Maintainer m);

    int updateMaintainerPwd(int mid,String pwd);

    List<Maintainer> queryMaintainer();

    Maintainer queryMaintainerByMid(int mid);

    int queryMaintainerByMidPwd(Maintainer m);

}
