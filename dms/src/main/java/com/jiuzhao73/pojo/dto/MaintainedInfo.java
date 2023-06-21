package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Maintained;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.sql.Date;

public class MaintainedInfo {
    @Min(value = 0, message = "维修id错误")
    private int mid;
    @Min(value = 0, message = "物品号错误")
    private int wid;
    @Length(max = 10, message = "财产名最长10个字符")
    private String wealthName;
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

    public String getWealthName() {
        return wealthName;
    }

    public void setWealthName(String wealthName) {
        this.wealthName = wealthName;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    public static MaintainedInfo toMaintainedInfo(Maintained maintained, String name) {
        if (maintained == null || maintained.isEmpty()) {
            return Constants.EMPTY_MAINTAINED_INFO;
        }
        return new MaintainedInfo(maintained.getMid(), maintained.getWid(), name, maintained.getMaintainTime());
    }

    public static Maintained parseMaintained(MaintainedInfo maintainedInfo) {
        if (maintainedInfo == null || isEmpty(maintainedInfo)) {
            return Constants.EMPTY_MAINTAINED;
        }
        return new Maintained(maintainedInfo.getMid(), maintainedInfo.getWid(), maintainedInfo.getMaintainTime());
    }

    public static boolean isEmpty(MaintainedInfo maintainedInfo) {
        return maintainedInfo == null || maintainedInfo.equals(Constants.EMPTY_MAINTAINED_INFO);
    }

    public MaintainedInfo(@Min(value = 0, message = "维修id错误") int mid,
                          @Min(value = 0, message = "物品号错误") int wid,
                          @Length(max = 10, message = "财产名最长10个字符") String wealthName,
                          Date maintainTime) {
        this.mid = mid;
        this.wid = wid;
        this.wealthName = wealthName;
        this.maintainTime = maintainTime;
    }

    public MaintainedInfo() {
    }
}
