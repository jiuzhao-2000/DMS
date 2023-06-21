package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class StudentLoginInfo {
    @Length(max = 10, message = "账号最长不能超过10位")
    @NotNull(message = "账号不为能为空")
    private String sid;
    @Length(max = 10, message = "密码最长不能超过10位")
    @NotNull(message = "密码不为能为空")
    private String pwd;

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

    public StudentLoginInfo() {
    }

    public StudentLoginInfo(@Length(max = 10, message = "账号最长不能超过10位")
                            @NotNull(message = "账号不为能为空") String sid,
                            @Length(max = 10, message = "密码最长不能超过10位")
                            @NotNull(message = "密码不为能为空") String pwd) {
        this.sid = sid;
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLoginInfo that = (StudentLoginInfo) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(pwd, that.pwd);
    }

    @Override
    public String toString() {
        return "StudentLoginInfo{" +
                "sid='" + sid + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, pwd);
    }

    public static boolean isEmpty(StudentLoginInfo studentInfo) {
        if (studentInfo == null) {
            return true;
        }
        return studentInfo.equals(Constants.EMPTY_STUDENT_LOGIN_INFO);
    }
}
