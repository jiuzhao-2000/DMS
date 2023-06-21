package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AdminLoginInfo {
    @Length(max = 11, message = "aid长度不能超过11")
    @NotNull(message = "账号不为能为空")
    private String aid;
    @Length(max = 10, message = "pwd长度不能超过10")
    @NotNull(message = "密码不为能为空")
    private String pwd;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public AdminLoginInfo() {
    }

    public AdminLoginInfo(@NotNull(message = "账号不为能为空") String aid,
                          @NotNull(message = "密码不为能为空") String pwd) {
        this.aid = aid;
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminLoginInfo that = (AdminLoginInfo) o;
        return Objects.equals(aid, that.aid) &&
                Objects.equals(pwd, that.pwd);
    }

    @Override
    public String toString() {
        return "AdminLoginInfo{" +
                "aid='" + aid + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, pwd);
    }

    public static boolean isEmpty(AdminLoginInfo adminLoginInfo) {
        if (adminLoginInfo == null ) {
            return true;
        }
        return adminLoginInfo.equals(Constants.EMPTY_ADMIN_LOGIN_INFO);
    }
}
