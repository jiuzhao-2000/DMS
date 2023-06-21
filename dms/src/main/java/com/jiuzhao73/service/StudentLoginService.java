package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;

public interface StudentLoginService {
    StudentInfo login(StudentLoginInfo info);
}
