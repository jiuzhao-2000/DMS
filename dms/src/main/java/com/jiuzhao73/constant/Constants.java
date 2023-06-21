package com.jiuzhao73.constant;

import com.jiuzhao73.pojo.dto.*;
import com.jiuzhao73.pojo.model.*;

import java.sql.Date;
import java.util.ArrayList;

public class Constants {
    public static final String ADMIN_SESSION = "admin";
    public static final String MAINTAIN_SESSION = "maintain";
    public static final String STUDENT_SESSION = "student";
    public static final String MESSAGE_SESSION = "msg";
    public static final String PAGE_HELPER_SESSION = "pageHelper";
    /**
     * Empty dto
     */
    public static final AdministratorInfo EMPTY_ADMINISTRATOR_INFO = new AdministratorInfo("", "", "", new ArrayList<>());
    public static final RepairListInfo EMPTY_REPAIR_LIST_INFO = new RepairListInfo(0, new Date(0));
    public final static AdminLoginInfo EMPTY_ADMIN_LOGIN_INFO = new AdminLoginInfo("", "");
    public final static DormitoryInfo EMPTY_DORMITORY_INFO = new DormitoryInfo(0, 0, "", -1, 0, -1, false, -1);
    public final static MaintainLoginInfo EMPTY_MAINTAIN_LOGIN_INFO = new MaintainLoginInfo(0, "");
    public static final RepairInfo EMPTY_REPAIR_INFO = new RepairInfo(0, 0, "", "");
    public static final MaintainerInfo EMPTY_MAINTAINER_INFO = new MaintainerInfo(0, "", "", 0);
    public final static StudentInfo EMPTY_STUDENT_INFO = new StudentInfo(
            "", "", "", "", 0, 0, "", ""
    );
    public static final StudentLoginInfo EMPTY_STUDENT_LOGIN_INFO = new StudentLoginInfo("", "");
    public final static VisitorInfo EMPTY_VISITOR_INFO = new VisitorInfo(
            "0", "", "", new Date(0), new Date(0), "", ""
    );
    public final static WealthInfo EMPTY_WEALTH_INFO = new WealthInfo(
            0, 0, "", "", 0
    );
    public static final MaintainedInfo EMPTY_MAINTAINED_INFO = new MaintainedInfo(0, 0, "", new Date(0));
    /**
     * Empty model
     */
    public static final Administrator EMPTY_ADMINISTRATOR = new Administrator("", "", "", "");
    public static final Administration EMPTY_ADMINISTRATION = new Administration("", 0, new java.util.Date(0));
    public static final Building EMPTY_BUILDING = new Building(0, "", "", "");
    public static final Dormitory EMPTY_DORMITORY = new Dormitory(0, 0, -1, 0, -1, false, -1);
    public static final Maintained EMPTY_MAINTAINED = new Maintained(0, 0, new Date(0));
    public static final Maintainer EMPTY_MAINTAINER = new Maintainer(0, "", "", "");
    public static final Repair EMPTY_REPAIR = new Repair(0, 0, "");
    public static final RepairList EMPTY_REPAIR_LIST = new RepairList(0, new Date(0));
    public static final Student EMPTY_STUDENT = new Student("", "", "", "", 0, 0, 0, "", "");
    public static final Visit EMPTY_VISIT = new Visit("", "", new Date(0), new Date(0), "");
    public static final Visitor EMPTY_VISITOR = new Visitor("", "", "");
    public static final Wealth EMPTY_WEALTH = new Wealth(0, 0, "", 0, 0);

}
