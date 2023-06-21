package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.mapper.StudentMapper;
import com.jiuzhao73.mapper.VisitMapper;
import com.jiuzhao73.mapper.VisitorMapper;
import com.jiuzhao73.pojo.dto.VisitorInfo;
import com.jiuzhao73.pojo.model.Visit;
import com.jiuzhao73.pojo.model.Visitor;
import com.jiuzhao73.service.VisitorService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorServiceImpl implements VisitorService {
    private final VisitorMapper visitorMapper;
    private final VisitMapper visitMapper;
    private final StudentMapper studentMapper;

    public VisitorServiceImpl(VisitorMapper visitorMapper, VisitMapper visitMapper, StudentMapper studentMapper) {
        this.visitorMapper = visitorMapper;
        this.visitMapper = visitMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public Result<List<VisitorInfo>> queryVisitorListBySid(String sid, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.error("页码出错", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Visit> visits = visitMapper.queryVisitBySid(sid);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (visits == null || visits.isEmpty()) {
            return Result.warning("没有查询到参观者信息，或没有参观者", new ArrayList<>());
        }
        String sName = studentMapper.queryStudentNameBySid(sid);
        List<VisitorInfo> infos = visits.stream()
                .map(
                        visit -> VisitorInfo.parseVisitorInfo(
                                visitorMapper.queryVisitorByVid(visit.getVid()), visit, sName
                        )
                )
                .collect(Collectors.toList());
        return Result.success("", infos, pageInfo);
    }

    @Override
    @Transactional
    public Result<Boolean> createVisitor(VisitorInfo info, String sid) {
        if (info == null || VisitorInfo.isEmpty(info)) {
            return Result.warning("参观者信息为空", false);
        }
        Visitor visitor = VisitorInfo.toVisitor(info);
        Visit visit = VisitorInfo.toVisit(info, sid);
        if (visitorMapper.queryVisitorNumByVid(info.getVid()) != 1 && visitorMapper.createVisitor(visitor) != 1) {
            return Result.error("创建参观者失败", false);
        }
        if (visitMapper.createVisit(visit) == 1) {
            return Result.success("创建成功，vid：" + info.getVid(), true);
        }
        return Result.error("创建失败，vid：" + info.getVid(), false);
    }

    @Override
    public Result<Boolean> updateVisitor(VisitorInfo info, String sid) {
        if (info == null || VisitorInfo.isEmpty(info) || sid == null || sid.isEmpty()) {
            return Result.warning("修改信息为空", false);
        }
        Visitor visitor = VisitorInfo.toVisitor(info);
        if (visitorMapper.updateVisitor(visitor) == 1) {
            return Result.success("修改参观者(vid:" + info.getVid() + ")成功", true);
        }
        return Result.error("修改参观者(vid:" + info.getVid() + ")失败", false);
    }
}
