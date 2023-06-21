package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper {
    int createAdministrator(Administrator a);

    int deleteAdministrator(String aid);

    int updateAdministratorPwd(Administrator a);

    int updateAdministratorPhone(Administrator a);

    int queryAdministratorByAidPwd(Administrator a);

    Administrator queryAdministrator(String aid);
}
