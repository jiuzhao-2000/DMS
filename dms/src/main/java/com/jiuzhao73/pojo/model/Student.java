package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.StudentLoginInfo;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Student implements IsEmpty {
    private String sid;
    private String pwd;
    private String name;
    private String sex;
    private int bid;
    private int did;
    private int bedId;
    private String state;
    private String phone;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
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

    public Student() {
    }

    public Student(String sid, String pwd, String name, String sex, int bid, int did, int bedId, String state, String phone) {
        this.sid = sid;
        this.pwd = pwd;
        this.name = name;
        this.sex = sex;
        this.bid = bid;
        this.did = did;
        this.bedId = bedId;
        this.state = state;
        this.phone = phone;
    }

    public Student(String sid, String pwd) {
        this.sid = sid;
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sid, student.sid) &&
                Objects.equals(pwd, student.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, pwd);
    }

    public static StudentLoginInfo toStudentLoginInfo(Student s) {
        return new StudentLoginInfo(s.sid, s.pwd);
    }

    public static Student parseStudent(StudentLoginInfo s) {
        return new Student(s.getSid(), s.getPwd());
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", bid=" + bid +
                ", dId=" + did +
                ", bedId=" + bedId +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_STUDENT);
    }

    public boolean isSameDorm(Student s) {
        return s.bid == bid && s.did == did;
    }

    public boolean isSameBedNum(Student s) {
        return s.bid == bid && s.did == did && s.bedId == bedId;
    }
}
