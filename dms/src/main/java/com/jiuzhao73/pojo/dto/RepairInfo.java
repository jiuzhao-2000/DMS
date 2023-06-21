package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Repair;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.util.Objects;

public class RepairInfo {
    @Min(value = 0, message = "rid不能小于0")
    private int rid;
    @Min(value = 0, message = "wid不能小于0")
    private int wid;
    @Length(max = 10, message = "姓名不能超过10个字符")
    private String name;
    @Length(max = 10, message = "问题长度不能超过10个字符")
    private String question;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairInfo that = (RepairInfo) o;
        return wid == that.wid &&
                rid == that.rid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wid, name, question);
    }

    public static RepairInfo parseRepairInfo(Repair repair, String name) {
        if (repair == null || repair.isEmpty() || name == null || name.isEmpty()) {
            return Constants.EMPTY_REPAIR_INFO;
        }
        return new RepairInfo(repair.getRid(), repair.getWid(), name, repair.getQuestion());
    }

    public static Repair toRepair(RepairInfo info) {
        if (info == null || RepairInfo.isEmpty(info)) {
            return Constants.EMPTY_REPAIR;
        }
        return new Repair(info.rid, info.wid, info.question);
    }

    public RepairInfo() {
    }

    public RepairInfo(@Min(value = 0, message = "rid不能小于0") int rid,
                      @Min(value = 0, message = "wid不能小于0") int wid,
                      @Length(max = 10, message = "姓名不能超过10个字符") String name,
                      @Length(max = 10, message = "问题长度不能超过10个字符") String question) {
        this.rid = rid;
        this.wid = wid;
        this.name = name;
        this.question = question;
    }

    @Override
    public String toString() {
        return "RepairInfo{" +
                "rid=" + rid +
                ", wid=" + wid +
                ", name='" + name + '\'' +
                ", question='" + question + '\'' +
                '}';
    }

    public static boolean isEmpty(RepairInfo repairInfo) {
        if (repairInfo == null) {
            return true;
        }
        return repairInfo.equals(Constants.EMPTY_REPAIR_INFO);
    }
}
