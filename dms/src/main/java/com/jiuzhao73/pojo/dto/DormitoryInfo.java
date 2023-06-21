package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Dormitory;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class DormitoryInfo {
    @Min(value = 0, message = "did错误")
    private int did;
    @Min(value = 0, message = "bid错误")
    private int bid;
    @Length(max = 10, message = "建筑名不错超过10个字")
    private String buildingName;
    @Min(value = 0, message = "宿舍床异常")
    private int occupancy;
    @Min(value = 0, message = "床号异常")
    private int bedNum;
    @Min(value = 0, message = "水电费不能小于0")
    private double waterAndElectricity;
    private boolean isPay;
    @Min(value = 0, message = "卫生评分不能低于0")
    @Max(value = 100, message = "卫生平时不能高于100")
    private int health;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public double getWaterAndElectricity() {
        return waterAndElectricity;
    }

    public void setWaterAndElectricity(double waterAndElectricity) {
        this.waterAndElectricity = waterAndElectricity;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DormitoryInfo that = (DormitoryInfo) o;
        return did == that.did &&
                bid == that.bid &&
                occupancy == that.occupancy &&
                bedNum == that.bedNum &&
                Double.compare(that.waterAndElectricity, waterAndElectricity) == 0 &&
                isPay == that.isPay &&
                health == that.health &&
                Objects.equals(buildingName, that.buildingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, bid, buildingName, occupancy, bedNum, waterAndElectricity, isPay, health);
    }

    public DormitoryInfo(@Min(value = 0, message = "did错误") int did,
                         @Min(value = 0, message = "bid错误") int bid,
                         @Length(max = 10, message = "建筑名不错超过10个字") String buildingName,
                         @Min(value = 0, message = "宿舍床异常") int occupancy,
                         @Min(value = 0, message = "床号异常") int bedNum,
                         @Min(value = 0, message = "水电费不能小于0") double waterAndElectricity,
                         boolean isPay,
                         @Min(value = 0, message = "卫生评分不能低于0")
                         @Max(value = 100, message = "卫生平时不能高于100")
                                 int health) {
        this.did = did;
        this.bid = bid;
        this.buildingName = buildingName;
        this.occupancy = occupancy;
        this.bedNum = bedNum;
        this.waterAndElectricity = waterAndElectricity;
        this.isPay = isPay;
        this.health = health;
    }

    public DormitoryInfo() {
    }

    public static Dormitory toDormitory(DormitoryInfo dormitoryInfo) {
        if (dormitoryInfo == null) {
            return new Dormitory();
        }
        return new Dormitory(dormitoryInfo.did, dormitoryInfo.bid,
                dormitoryInfo.occupancy, dormitoryInfo.bedNum,
                dormitoryInfo.waterAndElectricity, dormitoryInfo.isPay,
                dormitoryInfo.health);
    }

    public static DormitoryInfo parseDormitoryInfo(Dormitory dormitory, String buildingName) {
        if (dormitory == null) {
            return new DormitoryInfo();
        }
        return new DormitoryInfo(dormitory.getDid(), dormitory.getBid(),
                buildingName, dormitory.getOccupancy(), dormitory.getBedNum(),
                dormitory.getWaterAndElectricity(), dormitory.isPay(),
                dormitory.getHealth());
    }

    public static boolean isEmpty(DormitoryInfo dormitoryInfo) {
        if (dormitoryInfo == null) {
            return true;
        }
        return dormitoryInfo.equals(Constants.EMPTY_DORMITORY_INFO);
    }

}
