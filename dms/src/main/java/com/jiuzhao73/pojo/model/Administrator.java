package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.AdminLoginInfo;
import com.jiuzhao73.pojo.dto.AdministratorInfo;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Component
public class Administrator implements IsEmpty {
    private String aid;
    private String pwd;
    private String name;
    private String phone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Administrator(String aid, String pwd, String name, String phone) {
        this.aid = aid;
        this.pwd = pwd;
        this.name = name;
        this.phone = phone;
    }

    public Administrator(String aid, String pwd) {
        this.aid = aid;
        this.pwd = pwd;
    }

    public Administrator() {
    }

    public static AdminLoginInfo toMaintainLoginInfo(@Validated Administrator a) {
        if (a == null || a.isEmpty()) {
            return Constants.EMPTY_ADMIN_LOGIN_INFO;
        }
        return new AdminLoginInfo(a.aid, a.pwd);
    }

    public static Administrator parseAdministrator(@Validated AdminLoginInfo a) {
        if (a == null || AdminLoginInfo.isEmpty(a)) {
            return Constants.EMPTY_ADMINISTRATOR;
        }
        return new Administrator(a.getAid(), a.getPwd());
    }

    public static AdministratorInfo toAdministratorInfo(Administrator a, List<String> adminBuildingName) {
        if (a == null || a.isEmpty()) {
            return Constants.EMPTY_ADMINISTRATOR_INFO;
        }
        return new AdministratorInfo(a.aid, a.name, a.phone, adminBuildingName);
    }

    public static Administrator parseAdministrator(AdministratorInfo a, String pwd) {
        if (a == null || AdministratorInfo.isEmpty(a)) {
            return Constants.EMPTY_ADMINISTRATOR;
        }
        return new Administrator(a.getAid(), pwd, a.getName(), a.getPhone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(aid, that.aid) &&
                Objects.equals(pwd, that.pwd) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, pwd, name, phone);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "aid='" + aid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_ADMINISTRATOR);
    }
}
