package com.jiuzhao73.service;

import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.util.Result;

import java.util.List;

public interface StudentService {

    Result<Boolean> updateStudent(StudentLoginInfo info);

    Result<List<StudentInfo>> queryStudentList(int pageNum, int pageSize);

    Result<List<StudentInfo>> queryStudentListByBuildingName(String buildingName, int pageNum, int pageSize);

    Result<StudentInfo> queryStudentBySid(String sid);

    Result<List<StudentInfo>> queryStudentByName(String name, int pageNum, int pageSize);

    Result<List<StudentInfo>> queryStudentBySidName(String sid, String name,int pageNum,int pageSize);

    Result<Boolean> updateStudent(StudentInfo studentInfo);

    StudentInfo getStudentInfo(String sid);

    Result<Boolean> updateStudentInfo(StudentInfo info);

    Result<Boolean> updateStudentPhone(String sid, String phone);
}
