package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.dto.MaintainLoginInfo;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Component
public class Maintainer implements IsEmpty {
    private int mid;
    private String pwd;
    private String name;
    private String phone;

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

    public Maintainer() {
    }

    public Maintainer(int mid, String pwd) {
        this.mid = mid;
        this.pwd = pwd;
    }

    public Maintainer(int mid, String pwd, String name, String phone) {
        this.mid = mid;
        this.pwd = pwd;
        this.name = name;
        this.phone = phone;
    }

    public static MaintainLoginInfo toMaintainLoginInfo(@Validated Maintainer m) {
        if (m == null || m.isEmpty()) {
            return Constants.EMPTY_MAINTAIN_LOGIN_INFO;
        }
        return new MaintainLoginInfo(m.mid, m.pwd);
    }

    public static Maintainer parseMaintainer(@Validated MaintainLoginInfo m) {
        if (m == null || MaintainLoginInfo.isEmpty(m)) {
            return Constants.EMPTY_MAINTAINER;
        }
        return new Maintainer(m.getMid(), m.getPwd());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maintainer that = (Maintainer) o;
        return mid == that.mid &&
                Objects.equals(pwd, that.pwd) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, pwd, name, phone);
    }

    @Override
    public String toString() {
        return "Maintainer{" +
                "mid=" + mid +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_MAINTAINER);
    }
}
