package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

@Component
public class Wealth implements IsEmpty {
    private int wid;
    private double value;
    private String name;
    private int bid;
    private int did;

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

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public Wealth() {
    }

    public Wealth(int wid, double value, String name, int bid, int did) {
        this.wid = wid;
        this.value = value;
        this.name = name;
        this.bid = bid;
        this.did = did;
    }

    @Override
    public String toString() {
        return "Wealth{" +
                "wid=" + wid +
                ", value=" + value +
                ", name='" + name + '\'' +
                ", bid=" + bid +
                ", did=" + did +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_WEALTH);
    }
}
