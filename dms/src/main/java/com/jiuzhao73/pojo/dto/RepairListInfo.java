package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.RepairList;

import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.Objects;

public class RepairListInfo {
    @Min(value = 0, message = "rid不能小于0")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairListInfo that = (RepairListInfo) o;
        return rid == that.rid &&
                Objects.equals(repairTime, that.repairTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, repairTime);
    }

    public RepairListInfo() {
    }

    public RepairListInfo(@Min(value = 0, message = "rid不能小于0") int rid,
                          Date repairTime) {
        this.rid = rid;
        this.repairTime = repairTime;
    }

    public static RepairListInfo parseRepairListInfo(RepairList repairList) {
        if (repairList == null || repairList.isEmpty()) {
            return Constants.EMPTY_REPAIR_LIST_INFO;
        }
        return new RepairListInfo(repairList.getRid(), repairList.getRepairTime());
    }

    public static boolean isEmpty(RepairListInfo repairListInfo) {
        if (repairListInfo == null) {
            return true;
        }
        return repairListInfo.equals(Constants.EMPTY_REPAIR_LIST_INFO);
    }
}
