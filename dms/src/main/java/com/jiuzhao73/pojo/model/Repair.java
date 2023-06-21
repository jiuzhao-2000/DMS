package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Repair implements IsEmpty {
    private int rid;
    private int wid;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Repair() {
    }

    public Repair(int rid, int wid, String question) {
        this.rid = rid;
        this.wid = wid;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return rid == repair.rid &&
                wid == repair.wid &&
                Objects.equals(question, repair.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, wid, question);
    }

    @Override
    public String toString() {
        return "Repair{" +
                "rid=" + rid +
                ", wid=" + wid +
                ", question='" + question + '\'' +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_REPAIR);
    }
}
