package com.jiuzhao73.service.impl;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.AdministratorMapper;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.pojo.model.Administrator;
import com.jiuzhao73.service.AdminLoginService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    private final AdministratorMapper administratorMapper;

    @Override
    public Result<AdministratorInfo> login(AdminLoginInfo a) {
        if (a == null || AdminLoginInfo.isEmpty(a)) {
            return Result.warning("账号密码为空", Constants.EMPTY_ADMINISTRATOR_INFO);
        }
        Administrator administrator = Administrator.parseAdministrator(a);
        if (administratorMapper.queryAdministratorByAidPwd(administrator) > 0) {
            administrator = administratorMapper.queryAdministrator(administrator.getAid());
            AdministratorInfo info = Administrator.toAdministratorInfo(administrator, new ArrayList<>());
            return Result.success("登入成功", info);
        }
        return Result.warning("登入失败", Constants.EMPTY_ADMINISTRATOR_INFO);
    }

    public AdminLoginServiceImpl(AdministratorMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
    }
}
