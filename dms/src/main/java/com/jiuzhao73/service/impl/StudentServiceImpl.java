package com.jiuzhao73.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.mapper.BuildingMapper;
import com.jiuzhao73.mapper.DormitoryMapper;
import com.jiuzhao73.mapper.StudentMapper;
import com.jiuzhao73.pojo.dto.StudentInfo;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.pojo.model.Dormitory;
import com.jiuzhao73.pojo.model.Student;
import com.jiuzhao73.service.StudentService;
import com.jiuzhao73.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final BuildingMapper buildingMapper;
    private final DormitoryMapper dormitoryMapper;


    public StudentServiceImpl(StudentMapper studentMapper,
                              BuildingMapper buildingMapper,
                              DormitoryMapper dormitoryMapper) {
        this.studentMapper = studentMapper;
        this.buildingMapper = buildingMapper;
        this.dormitoryMapper = dormitoryMapper;
    }

    @Override
    public Result<Boolean> updateStudent(StudentLoginInfo info) {
        if (info == null || info.getSid() == null || info.getPwd() == null || info.getSid().isEmpty() || info.getPwd().isEmpty()) {
            return Result.error("账号密码不能为空", false);
        }
        Student student = Student.parseStudent(info);
        int num = studentMapper.queryStudentNumBySidPwd(student);
        if (num == 1) {
            return Result.warning("修改的密码与原密码一致", false);
        }
        if (studentMapper.updateStudentPwdBySid(info.getSid(), info.getPwd()) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败", false);
    }

    @Override
    public Result<List<StudentInfo>> queryStudentList(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.error("页码错误", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.queryStudent();
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (students == null || students.size() == 0) {
            return Result.warning("没有查询到学生或没有学生", new ArrayList<>());
        }
        List<StudentInfo> infos = students
                .stream()
                .map(this::toStudentInfo)
                .collect(Collectors.toList());
        return Result.success("查询成功", infos, pageInfo);
    }

    @Override
    public Result<List<StudentInfo>> queryStudentListByBuildingName(String buildingName,
                                                                    int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            return Result.error("页码错误", new ArrayList<>());
        }
        if (buildingName == null || buildingName.isEmpty()) {
            return queryStudentList(pageNum, pageSize);
        }
        Building building = buildingMapper.queryBuildingListByFullName(buildingName);
        if (building == null || Building.isEmpty(building)) {
            return Result.warning("该楼不存在，或查询错误", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.queryStudentByBid(building.getBid());
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (students == null || students.isEmpty()) {
            return Result.warning("该楼无学生，或查询错误", new ArrayList<>());
        }
        List<StudentInfo> data = students
                .stream()
                .map(this::toStudentInfo)
                .collect(Collectors.toList());
        return Result.success("查询成功", data, pageInfo);
    }

    @Override
    public Result<StudentInfo> queryStudentBySid(String sid) {
        if (sid == null || sid.isEmpty()) {
            return Result.warning("学号不能为空", Constants.EMPTY_STUDENT_INFO);
        }
        System.out.println(sid);
        Student student = studentMapper.queryStudentBySid(sid);
        System.out.println(student);
        if (student == null || student.isEmpty()) {
            return Result.warning("没有找到该学生", Constants.EMPTY_STUDENT_INFO);
        }
        return Result.success("查询成功", toStudentInfo(student));
    }

    @Override
    public Result<List<StudentInfo>> queryStudentByName(String name, int pageNum, int pageSize) {
        if (name == null || name.isEmpty() || pageNum < 1 || pageSize < 1) {
            return Result.error("姓名为空或者页码错误", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.queryStudentByName(name);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (students == null || students.isEmpty()) {
            return Result.success("没有学生，或者查询失败", new ArrayList<>());
        }
        List<StudentInfo> studentInfos = students
                .stream()
                .map(this::toStudentInfo)
                .collect(Collectors.toList());
        return Result.success("查询成功", studentInfos, pageInfo);
    }

    @Override
    public Result<List<StudentInfo>> queryStudentBySidName(String sid, String name, int pageNum, int pageSize) {
        if (sid == null || name == null || sid.isEmpty() || name.isEmpty()) {
            return Result.warning("", new ArrayList<>());
        }
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentMapper.queryStudentBySidName(sid, name);
        PageInfo<Object> pageInfo = page.toPageInfo();
        if (list == null || list.isEmpty()) {
            return Result.warning("没有找到", new ArrayList<>());
        }
        return Result.success("查询成功",
                list.stream()
                        .map(this::toStudentInfo)
                        .collect(Collectors.toList()),
                pageInfo);
    }

    private boolean updateDorm(Dormitory dormitory) {
        System.out.println("updateDorm");
        if (dormitory == null || dormitory.isEmpty()) {
            return false;
        }
        return dormitoryMapper.updateDormitory(dormitory) == 1;
    }

    private boolean updateStudent(Student student) {
        System.out.println("updateStudent");
        if (student == null || student.isEmpty()) {
            return false;
        }
        return studentMapper.updateStudent(student) == 1;
    }

    private Result<Boolean> updateStudentDorm(Student student) {
        System.out.println("updateStudentDorm");
        Dormitory dormitory = dormitoryMapper.queryDormitory(student.getDid(), student.getBid());
        dormitory = Dormitory.addBedId(dormitory, student.getBedId());
        if (updateDorm(dormitory) && updateStudent(student)) {
            return Result.success("修改成功", true);
        }
        return Result.warning("修改宿舍和学生失败", false);
    }

    private Result<Boolean> updateOnlyStu(Student student) {
        if (studentMapper.updateStudent(student) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.warning("修改学生失败", false);
    }

    /**
     * @param newStudent 修改后的学生信息
     * @param oldStudent 修改前的学生信息
     */
    private Result<Boolean> updateStudentDifferentDorm(Student newStudent, Student oldStudent) {
        System.out.println("updateStudentDifferentDorm");
        if (newStudent == null || oldStudent == null) {
            return Result.warning("学生信息不能为空", false);
        }
        Dormitory newDormitory = dormitoryMapper.queryDormitory(newStudent.getDid(), newStudent.getBid());
        Dormitory oldDormitory = dormitoryMapper.queryDormitory(oldStudent.getDid(), oldStudent.getBid());
        //想搬进去的宿舍没有床位返回warning,
        if (!Dormitory.haveResidueBed(newDormitory.getOccupancy(), newStudent.getBedId())) {
            return Result.warning("该宿舍床位已有人", false);
        }
        newDormitory = Dormitory.addBedId(newDormitory, newStudent.getBedId());
        oldDormitory = Dormitory.deleteBedId(oldDormitory, oldStudent.getBedId());
        if (dormitoryMapper.updateDormitory(newDormitory) != 1) {
            return Result.warning("修改新宿舍信息失败", false);
        }
        if (dormitoryMapper.updateDormitory(oldDormitory) != 1) {
            return Result.warning("修改旧宿舍信息失败", false);
        }
        if (studentMapper.updateStudent(newStudent) == 1) {
            return Result.success("修改成功", false);
        }
        return Result.warning("修改学生失败", false);
    }

    /**
     * 1.尚未分配宿舍
     * 1)修改宿舍住宿信息
     * 2)修改学生信息
     * 2.早已分配宿舍
     * 2.1.宿舍改变
     * 1)修改现宿舍住宿信息
     * 2)修改后宿舍住宿信息
     * 3)修改学生信息
     * 2.2.宿舍未改变
     * 2.2.1.床号改变
     * 1)修改学生信息
     * 2.2.2.床号未改变
     * 1)修改学生信息
     */
    @Override
    @Transactional
    public Result<Boolean> updateStudent(StudentInfo studentInfo) {
        //合法性判断
        if (studentInfo == null || StudentInfo.isEmpty(studentInfo)) {
            return Result.warning("修改的学生信息为空", false);
        }
        Student newStudent = toStudent(studentInfo);
        Student oldStudent = studentMapper.queryStudentBySid(studentInfo.getSid());
        //尚未分配宿舍
        if (oldStudent.getBid() <= 0) {
            return updateStudentDorm(newStudent);
        }
        //早已分配宿舍,但宿舍未改变
        if (newStudent.isSameBedNum(oldStudent)) {
            return updateOnlyStu(newStudent);
        }
        //换宿舍
        return updateStudentDifferentDorm(newStudent, oldStudent);
    }

    private StudentInfo toStudentInfo(Student s) {
        if (s == null || s.isEmpty()) {
            return Constants.EMPTY_STUDENT_INFO;
        }
        Building building = buildingMapper.queryBuildingByBid(s.getBid());
        if (building == null || Building.isEmpty(building)) {
            return Constants.EMPTY_STUDENT_INFO;
        }
        return StudentInfo.toStudentInfo(s, building.getName());
    }

    private Student toStudent(StudentInfo s) {
        if (s == null || StudentInfo.isEmpty(s)) {
            return Constants.EMPTY_STUDENT;
        }
        Building building = buildingMapper.queryBuildingListByFullName(s.getBuildingName());
        if (building == null || Building.isEmpty(building)) {
            return Constants.EMPTY_STUDENT;
        }
        return StudentInfo.parseStudent(s, building);
    }

    @Override
    public StudentInfo getStudentInfo(String sid) {
        if (sid == null || sid.isEmpty()) {
            return Constants.EMPTY_STUDENT_INFO;
        }
        Student student = studentMapper.queryStudentBySid(sid);
        Building building = buildingMapper.queryBuildingByBid(student.getBid());
        return StudentInfo.toStudentInfo(student, building.getName());
    }

    @Override
    public Result<Boolean> updateStudentInfo(StudentInfo info) {
        if (info == null || StudentInfo.isEmpty(info)) {
            return Result.warning("修改的信息为空", false);
        }
        Building building = buildingMapper.queryBuildingListByFullName(info.getBuildingName());
        Student student = StudentInfo.parseStudent(info, building);
        if (studentMapper.updateStudent(student) == 1) {
            return Result.success("修改成功", true);
        }
        return Result.error("修改失败，请稍后在操作", false);
    }

    @Override
    public Result<Boolean> updateStudentPhone(String sid, String phone) {
        if (sid == null || sid.isEmpty() || phone == null || phone.isEmpty()) {
            return Result.warning("修改的电话为空", false);
        }
        if (studentMapper.updateStudentPhoneBySid(sid, phone) == 1) {
            return Result.success("修改成功，电话改为：" + phone, true);
        }
        return Result.error("修改失败，请稍后在尝试", false);
    }
}
