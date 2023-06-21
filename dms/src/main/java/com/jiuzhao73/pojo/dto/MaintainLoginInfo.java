package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MaintainLoginInfo {
    @NotNull(message = "账号不为能为空")
    @Length(max = 10, message = "mid长度不能超过11")
    private int mid;
    @NotNull(message = "密码不为能为空")
    @Length(max = 10, message = "pwd长度不能超过11")
    private String pwd;


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintainLoginInfo that = (MaintainLoginInfo) o;
        return mid == that.mid &&
                Objects.equals(pwd, that.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, pwd);
    }

    public MaintainLoginInfo() {
    }

    public MaintainLoginInfo(@NotNull(message = "账号不为能为空") int mid,
                             @NotNull(message = "密码不为能为空") String pwd) {
        this.mid = mid;
        this.pwd = pwd;
    }

    public static boolean isEmpty(MaintainLoginInfo maintainLoginInfo) {
        if (maintainLoginInfo == null) {
            return true;
        }
        return maintainLoginInfo.equals(Constants.EMPTY_MAINTAIN_LOGIN_INFO);
    }

}
