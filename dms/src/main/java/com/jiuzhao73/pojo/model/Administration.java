package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class Administration implements IsEmpty {
    private String aid;
    private int bid;
    private Date workTime;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Administration(String aid, int bid, Date workTime) {
        this.aid = aid;
        this.bid = bid;
        this.workTime = workTime;
    }

    public Administration() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administration that = (Administration) o;
        return bid == that.bid &&
                Objects.equals(aid, that.aid) &&
                Objects.equals(workTime, that.workTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, bid, workTime);
    }

    @Override
    public String toString() {
        return "Administration{" +
                "aid='" + aid + '\'' +
                ", bid=" + bid +
                ", workTime=" + workTime +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_ADMINISTRATION);
    }
}
