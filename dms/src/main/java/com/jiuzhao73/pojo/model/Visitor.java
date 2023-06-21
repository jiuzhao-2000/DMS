package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Visitor implements IsEmpty {
    private String vid;
    private String name;
    private String phone;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Visitor() {
    }

    public Visitor(String vid, String name, String phone) {
        this.vid = vid;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "vid='" + vid + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(vid, visitor.vid) &&
                Objects.equals(name, visitor.name) &&
                Objects.equals(phone, visitor.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vid, name, phone);
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_VISITOR);
    }
}
