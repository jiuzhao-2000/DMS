package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Building {
    private int bid;
    private String name;
    private String phone;
    private String location;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Building(int bid, String phone, String name, String location) {
        this.bid = bid;
        this.phone = phone;
        this.name = name;
        this.location = location;
    }

    public Building() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return bid == building.bid &&
                Objects.equals(phone, building.phone) &&
                Objects.equals(name, building.name) &&
                Objects.equals(location, building.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, phone, name, location);
    }

    @Override
    public String toString() {
        return "Building{" +
                "bid=" + bid +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public static boolean isEmpty(Building building) {
        if (building == null) {
            return true;
        }
        return building.equals(Constants.EMPTY_BUILDING);
    }
}
