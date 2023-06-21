package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Maintainer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class MaintainerInfo {
    @Min(value = 0, message = "mid最小不能小于0")
    private int mid;
    @Length(max = 10, message = "姓名长度不能错过10")
    private String name;
    @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "请输入正确的电话号码")
    private String phone;
    @Min(value = 0, message = "工作次数不能小于0")
    private int workNum;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public int getWorkNum() {
        return workNum;
    }

    public void setWorkNum(int workNum) {
        this.workNum = workNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintainerInfo that = (MaintainerInfo) o;
        return workNum == that.workNum &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, workNum);
    }

    public MaintainerInfo(@Min(value = 0, message = "mid最小不能小于0") int mid,
                          @Length(max = 10, message = "姓名长度不能错过10") String name,
                          @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$",
                                  message = "请输入正确的电话号码") String phone,
                          @Min(value = 0, message = "工作次数不能小于0") int workNum) {
        this.mid = mid;
        this.name = name;
        this.phone = phone;
        this.workNum = workNum;
    }

    public MaintainerInfo() {
    }

    public static MaintainerInfo toMaintainerInfo(Maintainer maintainer, int workNum) {
        if (maintainer == null || maintainer.isEmpty()) {
            return Constants.EMPTY_MAINTAINER_INFO;
        }
        return new MaintainerInfo(maintainer.getMid(), maintainer.getName(), maintainer.getPhone(), workNum);
    }

    public static Maintainer parseMaintainer(MaintainerInfo info, String pwd) {
        if (info == null || isEmpty(info)) {
            return Constants.EMPTY_MAINTAINER;
        }
        return new Maintainer(info.mid, pwd, info.name, info.phone);
    }

    public static boolean isEmpty(MaintainerInfo maintainerInfo) {
        if (maintainerInfo == null) {
            return true;
        }
        return maintainerInfo.equals(Constants.EMPTY_MAINTAINER_INFO);
    }

    @Override
    public String toString() {
        return "MaintainerInfo{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", workNum=" + workNum +
                '}';
    }
}
