package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Administrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdministratorMapperTest {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Test
    void queryAdministrator() {
        System.out.println(administratorMapper.queryAdministrator("100001"));
    }

    @Test
    void updateAdministratorPwd() {
        Administrator administrator = new Administrator("100006", "123456", "jiuzhao", "18300000000");
        System.out.println(administratorMapper.updateAdministratorPwd(administrator));
    }

    @Test
    void updateAdministratorPhone() {
        Administrator administrator = new Administrator("100006", "123456", "jiuzhao", "18300000000");
        System.out.println(administratorMapper.updateAdministratorPhone(administrator));
    }

    @Test
    void deleteAdministrator() {
        System.out.println(administratorMapper.deleteAdministrator(""));
    }

    @Test
    void createAdministrator() {
        Administrator administrator = new Administrator("100007", "123456", "jiuzhao", "18300000000");
        System.out.println(administratorMapper.createAdministrator(administrator));
    }
}
