package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Administration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class AdministrationMapperTest {
    @Autowired
    private AdministrationMapper administrationMapper;

    @Test
    void createAdministration() {
        Administration administration = new Administration("100001", 10, new Date(System.currentTimeMillis()));
        System.out.println(administrationMapper.createAdministration(administration));
    }

    @Test
    void deleteAdministration() {
        System.out.println(administrationMapper.deleteAdministration("100001", 10));
    }

    @Test
    void updateAdministration() {
        Administration administration = new Administration("100001", 10, new Date(System.currentTimeMillis()));
        System.out.println(administrationMapper.updateAdministration(administration));
    }

    @Test
    void queryAdministrationListByAid() {
        System.out.println(administrationMapper.queryAdministrationListByAid("100001"));
    }

    @Test
    void queryAdministrationList() {
        System.out.println(administrationMapper.queryAdministrationListByAid("100001"));
    }
}
