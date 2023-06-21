package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.util.Result;

public interface AdminLoginService {

    Result<AdministratorInfo> login(AdminLoginInfo a);

}
