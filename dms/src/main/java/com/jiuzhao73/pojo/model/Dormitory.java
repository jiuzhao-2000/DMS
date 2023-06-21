package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Dormitory implements IsEmpty {
    private int did;
    private int bid;
    private int occupancy;
    private int bedNum;
    private double waterAndElectricity;
    private boolean isPay;
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

    public Dormitory(int did, int bid, int occupancy, int bedNum, double waterAndElectricity, boolean isPay, int health) {
        this.did = did;
        this.bid = bid;
        this.occupancy = occupancy;
        this.bedNum = bedNum;
        this.waterAndElectricity = waterAndElectricity;
        this.isPay = isPay;
        this.health = health;
    }

    public Dormitory() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dormitory dormitory = (Dormitory) o;
        return did == dormitory.did &&
                bid == dormitory.bid &&
                occupancy == dormitory.occupancy &&
                bedNum == dormitory.bedNum &&
                Double.compare(dormitory.waterAndElectricity, waterAndElectricity) == 0 &&
                isPay == dormitory.isPay &&
                health == dormitory.health;
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, bid, occupancy, bedNum, waterAndElectricity, isPay, health);
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "did=" + did +
                ", bid=" + bid +
                ", occupancy=" + occupancy +
                ", bedNum=" + bedNum +
                ", waterAndElectricity=" + waterAndElectricity +
                ", isPay=" + isPay +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_DORMITORY);
    }

    /**
     * @param bedId 床号
     * @return 1)空床 return true 2)有人了 return false
     */
    public static boolean haveResidueBed(int occupancy, int bedId) {
        if (bedId < 1) {
            return false;
        }
        int index = 1 << (bedId - 1);
        return (occupancy & index) == 0;
    }

    /**
     * 向一个宿舍添加一个成员，床号为bedId
     * 宿舍已满，宿舍不改变
     * 传入空宿舍参数，返回空宿舍
     */
    public static Dormitory addBedId(Dormitory dormitory, int bedId) {
        if (dormitory == null || dormitory.isEmpty()) {
            return Constants.EMPTY_DORMITORY;
        }
        Dormitory rst = new Dormitory(dormitory.getDid(),
                dormitory.getBid(),
                dormitory.occupancy,
                dormitory.getBedNum(),
                dormitory.getWaterAndElectricity(),
                dormitory.isPay(),
                dormitory.getHealth());
        if (!haveResidueBed(dormitory.occupancy, bedId)) {
            return rst;
        }

        int index = 1 << (bedId - 1);
        rst.occupancy |= index;
        return rst;
    }

    public static Dormitory deleteBedId(Dormitory dormitory, int bedId) {
        if (dormitory == null || dormitory.isEmpty()) {
            return Constants.EMPTY_DORMITORY;
        }
        Dormitory rst = new Dormitory(dormitory.getDid(),
                dormitory.getBid(),
                dormitory.occupancy,
                dormitory.getBedNum(),
                dormitory.getWaterAndElectricity(),
                dormitory.isPay(),
                dormitory.getHealth());
        if (haveResidueBed(dormitory.occupancy, bedId)) {
            return rst;
        }
        int index = 0XFFFFFFFF - (1 << (bedId - 1));
        rst.occupancy &= index;
        return rst;
    }
}
