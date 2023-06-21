package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.VisitorInfo;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface VisitorService {
    Result<List<VisitorInfo>> queryVisitorListBySid(String sid, int pageNum, int pageSize);

    Result<Boolean> createVisitor(VisitorInfo info, String sid);

    Result<Boolean> updateVisitor(VisitorInfo info, String sid);
}
