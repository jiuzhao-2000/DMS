package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Dormitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DormitoryMapperTest {
    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Test
    void createDormitory() {
        Dormitory dormitory = new Dormitory(1,11,1,3,99.9,true,99);
        System.out.println(dormitoryMapper.createDormitory(dormitory));
    }

    @Test
    void deleteDormitory() {
        System.out.println(dormitoryMapper.deleteDormitory(1, 11));
    }

    @Test
    void updateDormitory() {
        Dormitory dormitory = new Dormitory(1,11,1,3,99.9,true,99);
        dormitoryMapper.updateDormitory(dormitory);
        dormitory=new Dormitory(1,11,-11,0,-1,true,-1);
        dormitoryMapper.updateDormitory(dormitory);
    }

    @Test
    void queryDormitoryByBid() {
        System.out.println(dormitoryMapper.queryDormitoryByBid(1));
    }

    @Test
    void queryDormitory() {
        System.out.println(dormitoryMapper.queryDormitory(1, 11));
    }
}
