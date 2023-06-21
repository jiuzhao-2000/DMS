package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    int createStudent(Student s);

    int deleteStudent(String sid);

    int updateStudent(@Param("s") Student s);

    int updateStudentPwdBySid(String sid,String pwd);

    int updateStudentPhoneBySid(String sid,String phone);

    List<Student> queryStudent();

    Student queryStudentBySid(String sid);

    int queryStudentNumBySidPwd(Student s);

    String queryStudentNameBySid(String sid);

    int queryStudentBidBySid(String sid);

    List<Student> queryStudentByBid(int bid);

    List<Student> queryStudentByName(String name);

    List<Student> queryStudentBySidName(String sid, String name);

}
