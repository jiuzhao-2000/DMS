package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Wealth;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.util.Objects;

public class WealthInfo {
    @Min(value = 0, message = "wid不能小于0")
    private int wid;
    @Min(value = 0, message = "价值不能小于0")
    private double value;
    @Length(max = 10, message = "姓名不能超过10个字符")
    private String name;
    @Length(max = 10, message = "楼名不能超过10个字符")
    private String buildingName;
    @Min(value = 0, message = "宿舍号不能小于0")
    private int did;

    public WealthInfo() {
    }

    public WealthInfo(@Min(value = 0, message = "wid不能小于0") int wid,
                      @Min(value = 0, message = "价值不能小于0") double value,
                      @Length(max = 10, message = "姓名不能超过10个字符") String name,
                      @Length(max = 10, message = "楼名不能超过10个字符") String buildingName,
                      @Min(value = 0, message = "宿舍号不能小于0") int did) {
        this.wid = wid;
        this.value = value;
        this.name = name;
        this.buildingName = buildingName;
        this.did = did;
    }

    public static WealthInfo parseWealthInfo(Wealth wealth, String buildingName) {
        if (wealth == null || wealth.isEmpty()) {
            return Constants.EMPTY_WEALTH_INFO;
        }
        return new WealthInfo(wealth.getWid(), wealth.getValue(), wealth.getName(), buildingName, wealth.getDid());
    }

    public static Wealth toWealth(WealthInfo wealthInfo, int bid) {
        if (wealthInfo == null || isEmpty(wealthInfo)) {
            return Constants.EMPTY_WEALTH;
        }
        return new Wealth(wealthInfo.getWid(), wealthInfo.getValue(), wealthInfo.getName(), bid, wealthInfo.getDid());
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WealthInfo that = (WealthInfo) o;
        return wid == that.wid &&
                Double.compare(that.value, value) == 0 &&
                did == that.did &&
                Objects.equals(name, that.name) &&
                Objects.equals(buildingName, that.buildingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wid, value, name, buildingName, did);
    }

    public static boolean isEmpty(WealthInfo wealthInfo) {
        if (wealthInfo == null) {
            return true;
        }
        return wealthInfo.equals(Constants.EMPTY_WEALTH_INFO);
    }

}
