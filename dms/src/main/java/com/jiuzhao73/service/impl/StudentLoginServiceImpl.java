package com.jiuzhao73.service.impl;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.BuildingMapper;
import com.jiuzhao73.mapper.StudentMapper;
import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.pojo.model.Student;
import com.jiuzhao73.service.StudentLoginService;
import org.springframework.stereotype.Service;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {

    private final StudentMapper studentMapper;
    private final BuildingMapper buildingMapper;

    @Override
    public StudentInfo login(StudentLoginInfo info) {
        if (info == null || StudentLoginInfo.isEmpty(info))
            return Constants.EMPTY_STUDENT_INFO;
        Student student = Student.parseStudent(info);
        if (studentMapper.queryStudentNumBySidPwd(student) > 0) {
            student = studentMapper.queryStudentBySid(info.getSid());
            Building building = buildingMapper.queryBuildingByBid(student.getBid());
            return StudentInfo.toStudentInfo(student, building.getName());
        }
        return Constants.EMPTY_STUDENT_INFO;
    }

    public StudentLoginServiceImpl(StudentMapper studentMapper, BuildingMapper buildingMapper) {
        this.studentMapper = studentMapper;
        this.buildingMapper = buildingMapper;
    }
}
