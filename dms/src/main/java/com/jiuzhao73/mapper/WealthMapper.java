package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Wealth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WealthMapper {
    int createWealth(Wealth w);

    int deleteWealth(int wid);

    int updateWealth(@Param("w") Wealth w);

    List<Wealth> queryWealthList(@Param("w") Wealth w);

    List<Wealth> queryWealthListByBidDid(int bid, int did);

    Wealth queryWealthByWid(int wid);

}
