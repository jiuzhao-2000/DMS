package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class RepairList implements IsEmpty {
    private int rid;
    private Date repairTime;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public RepairList() {
    }

    public RepairList(int rid, Date repairTime) {
        this.rid = rid;
        this.repairTime = repairTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairList that = (RepairList) o;
        return rid == that.rid &&
                Objects.equals(repairTime, that.repairTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, repairTime);
    }

    @Override
    public String toString() {
        return "RepairList{" +
                "rid=" + rid +
                ", repairTime=" + repairTime +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_REPAIR_LIST);
    }
}
