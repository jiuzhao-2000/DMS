package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Building;
import com.jiuzhao73.pojo.model.Student;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class StudentInfo {
    @Length(max = 10, message = "sid长度错误")
    private String sid;
    @Length(max = 10, message = "姓名长度错误")
    private String name;
    @Length(max = 1, message = "性别长度错误")
    private String sex;
    @Length(max = 10, message = "建筑名长度不能超过10个")
    private String buildingName;
    @Min(value = 0, message = "did不能低于0")
    private int did;
    @Min(value = 0, message = "bedId错误")
    private int bedId;
    @Size(min = 1, max = 10, message = "状态长度错误")
    private String state;
    @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "请输入正确的电话号码")
    private String phone;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static StudentInfo toStudentInfo(Student s, String buildingName) {
        if (s == null || buildingName == null || s.isEmpty() || buildingName.isEmpty()) {
            return Constants.EMPTY_STUDENT_INFO;
        }
        return new StudentInfo(s.getSid(), s.getName(), s.getSex(),
                buildingName, s.getDid(), s.getBedId(), s.getState(), s.getPhone());
    }

    public static Student parseStudent(StudentInfo s, Building b) {
        if (s == null || StudentInfo.isEmpty(s) || b == null || Building.isEmpty(b)) {
            return Constants.EMPTY_STUDENT;
        }
        return new Student(s.sid, "", s.name, s.sex, b.getBid(), s.did, s.bedId, s.state, s.phone);
    }

    public StudentInfo(String sid,
                       @NotNull(message = "姓名不能为空") @Size(min = 1, max = 10) String name,
                       @NotNull(message = "性别不能为空") @Size(min = 1, max = 1) String sex,
                       @NotNull(message = "宿舍楼名称不能为空") @Size(min = 1, max = 10) String buildingName,
                       int did,
                       int bedId,
                       @NotNull(message = "学生状态不能为空") @Size(min = 1, max = 10) String state,
                       String phone) {
        this.sid = sid;
        this.name = name;
        this.sex = sex;
        this.buildingName = buildingName;
        this.did = did;
        this.bedId = bedId;
        this.state = state;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInfo that = (StudentInfo) o;
        return Objects.equals(sid, that.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid);
    }

    public StudentInfo() {
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", did=" + did +
                ", bedId=" + bedId +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static boolean isEmpty(StudentInfo studentInfo) {
        if (studentInfo == null) {
            return true;
        }
        return studentInfo.equals(Constants.EMPTY_STUDENT_INFO);
    }

}
