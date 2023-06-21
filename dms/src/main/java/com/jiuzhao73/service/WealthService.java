package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.WealthInfo;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface WealthService {

    List<WealthInfo> queryWealthInfo(String sid);

    Result<List<WealthInfo>> queryWealthList(int bid, int did, int pageNum, int pageSize);

}
