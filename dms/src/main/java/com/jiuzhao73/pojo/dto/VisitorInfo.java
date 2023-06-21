package com.jiuzhao73.pojo.dto;

import com.jiuzhao73.constant.Constants;
import com.jiuzhao73.pojo.model.Visit;
import com.jiuzhao73.pojo.model.Visitor;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Objects;

@ApiModel("访客实体")
public class VisitorInfo {
    @Length(max = 18, message = "vid长度不能超过18位")
    private String vid;
    @Length(max = 10, message = "姓名不能超过10位")
    private String name;
    @Length(max = 10, message = "学生姓名不能超过10位")
    private String stuName;
    private Date visitTime;
    private Date visitedTime;
    @Length(max = 10, message = "关系不能超过10位")
    private String relationship;
    @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "请输入正确的电话号码")
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

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public VisitorInfo() {
    }

    public VisitorInfo(@Length(max = 18, message = "vid长度不能超过18位") String vid,
                       @Length(max = 10, message = "姓名不能超过10位") String name,
                       @Length(max = 10, message = "学生姓名不能超过10位") String stuName,
                       Date visitTime,
                       Date visitedTime,
                       @Length(max = 10, message = "关系不能超过10位") String relationship,
                       @Pattern(regexp = "1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$",
                               message = "请输入正确的电话号码") String phone) {
        this.vid = vid;
        this.name = name;
        this.stuName = stuName;
        this.visitTime = visitTime;
        this.visitedTime = visitedTime;
        this.relationship = relationship;
        this.phone = phone;
    }

    public static Visitor toVisitor(VisitorInfo info) {
        if (info == null || isEmpty(info)) {
            return Constants.EMPTY_VISITOR;
        }
        return new Visitor(info.vid, info.name, info.phone);
    }

    public static Visit toVisit(VisitorInfo info, String sid) {
        if (info == null || isEmpty(info) || sid == null) {
            return Constants.EMPTY_VISIT;
        }
        return new Visit(sid, info.vid, info.visitTime, info.visitedTime, info.relationship);
    }

    public static VisitorInfo parseVisitorInfo(Visitor visitor, Visit visit, String stuName) {
        if (visitor == null || visitor.isEmpty() || visit == null || visit.isEmpty()) {
            return Constants.EMPTY_VISITOR_INFO;
        }
        if (!visitor.getVid().equals(visit.getVid())) {
            return Constants.EMPTY_VISITOR_INFO;
        }
        return new VisitorInfo(visit.getVid(), visitor.getName(), stuName, visit.getVisitTime(),
                visit.getVisitedTime(), visit.getRelationship(), visitor.getPhone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitorInfo that = (VisitorInfo) o;
        return Objects.equals(vid, that.vid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vid, name, stuName, visitTime, visitedTime, relationship, phone);
    }

    @Override
    public String toString() {
        return "VisitorInfo{" +
                "vid='" + vid + '\'' +
                ", name='" + name + '\'' +
                ", stuName='" + stuName + '\'' +
                ", visitTime=" + visitTime +
                ", visitedTime=" + visitedTime +
                ", relationship='" + relationship + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static boolean isEmpty(VisitorInfo visitorInfo) {
        if (visitorInfo == null) {
            return true;
        }
        return visitorInfo.equals(Constants.EMPTY_VISITOR_INFO);
    }

}
