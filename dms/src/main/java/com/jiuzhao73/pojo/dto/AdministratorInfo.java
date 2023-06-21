package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

public class AdministratorInfo {
    @Length(max = 11, message = "aid长度不能超过11")
    private String aid;
    @Length(max = 10, message = "姓名最长10个字符")
    private String name;
    @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "请输入正确的电话号码")
    private String phone;
    private List<String> adminBuildingName;

    public List<String> getAdminBuildingName() {
        return adminBuildingName;
    }

    public void setAdminBuildingName(List<String> adminBuildingName) {
        this.adminBuildingName = adminBuildingName;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministratorInfo that = (AdministratorInfo) o;
        return Objects.equals(aid, that.aid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, name, phone);
    }

    public AdministratorInfo(@Length(max = 11, message = "aid长度不能超过11") String aid,
                             @Length(max = 10, message = "姓名最长10个字符") String name,
                             @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$",
                                     message = "请输入正确的电话号码") String phone,
                             List<String> adminBuildingName) {
        this.aid = aid;
        this.name = name;
        this.phone = phone;
        this.adminBuildingName = adminBuildingName;
    }

    public AdministratorInfo() {
    }

    public static boolean isEmpty(AdministratorInfo administratorInfo) {
        if (administratorInfo == null) {
            return true;
        }
        return administratorInfo.equals(Constants.EMPTY_ADMINISTRATOR_INFO);
    }
}
