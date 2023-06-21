package com.jiuzhao73.pojo.model;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.util.IsEmpty;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class Visit implements IsEmpty {
    private String sid;
    private String vid;
    private Date visitTime;
    private Date visitedTime;
    private String relationship;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Date getVisitedTime() {
        return visitedTime;
    }

    public void setVisitedTime(Date visitedTime) {
        this.visitedTime = visitedTime;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Visit() {
    }

    public Visit(String sid, String vid, Date visitTime, Date visitedTime, String relationship) {
        this.sid = sid;
        this.vid = vid;
        this.visitTime = visitTime;
        this.visitedTime = visitedTime;
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "sid='" + sid + '\'' +
                ", vid='" + vid + '\'' +
                ", visitTime=" + visitTime +
                ", visitedTime=" + visitedTime +
                ", relationship='" + relationship + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(sid, visit.sid) &&
                Objects.equals(vid, visit.vid) &&
                Objects.equals(visitTime, visit.visitTime) &&
                Objects.equals(visitedTime, visit.visitedTime) &&
                Objects.equals(relationship, visit.relationship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, vid, visitTime, visitedTime, relationship);
    }

    @Override
    public boolean isEmpty() {
        return equals(Constants.EMPTY_VISIT);
    }
}
