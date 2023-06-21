package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class Maintained implements IsEmpty {
    private int mid;
    private int wid;
    private Date maintainTime;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    public Maintained(int mid, int wid, Date maintainTime) {
        this.mid = mid;
        this.wid = wid;
        this.maintainTime = maintainTime;
    }

    public Maintained() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maintained that = (Maintained) o;
        return mid == that.mid &&
                wid == that.wid &&
                Objects.equals(maintainTime, that.maintainTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, wid, maintainTime);
    }

    @Override
    public String toString() {
        return "Maintained{" +
                "mid=" + mid +
                ", wid=" + wid +
                ", maintainTime=" + maintainTime +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_MAINTAINED);
    }
}
