package com.jiuzhao73.service.impl;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.AdministratorMapper;
import com.jiuzhao73.mapper.BuildingMapper;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.pojo.model.Administrator;
import com.jiuzhao73.service.AdminService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdministratorMapper administratorMapper;
    private final BuildingMapper buildingMapper;

    public AdminServiceImpl(AdministratorMapper administratorMapper,
                            BuildingMapper buildingMapper) {
        this.administratorMapper = administratorMapper;
        this.buildingMapper = buildingMapper;
    }

    @Override
    public Result<AdministratorInfo> queryAdminByAid(String aid) {
        if (aid == null || aid.isEmpty()) {
            return Result.warning("", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        Administrator administrator = administratorMapper.queryAdministrator(aid);
        if (administrator == null) {
            return Result.warning("", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        List<String> buildingName = buildingMapper.queryBuildingNameListByAid(aid);
        AdministratorInfo info;
        if (buildingName == null || buildingName.isEmpty()) {
            info = Administrator.toAdministratorInfo(administrator, new ArrayList<>());
        } else {
            info = Administrator.toAdministratorInfo(administrator, buildingName);
        }
        return Result.success("查询成功", info);
    }

    @Override
    public Result<Boolean> updateAdmin(AdministratorInfo administratorInfo) {
        if (administratorInfo == null || AdministratorInfo.isEmpty(administratorInfo)) {
            return Result.warning("修改信息为空", false);
        }
        Administrator administrator = Administrator.parseAdministrator(administratorInfo, "");
        if (administratorMapper.updateAdministratorPhone(administrator) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }

    @Override
    public Result<Boolean> updateAdminAccount(AdminLoginInfo info) {
        if (info == null || AdminLoginInfo.isEmpty(info) || info.getPwd().isEmpty()) {
            return Result.warning("修改信息为空", false);
        }
        Administrator administrator = administratorMapper.queryAdministrator(info.getAid());
        if (administrator == null || administrator.isEmpty()) {
            return Result.warning("该用户不存在，请先注册", false);
        }
        if (administratorMapper.updateAdministratorPwd(Administrator.parseAdministrator(info)) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }
}
