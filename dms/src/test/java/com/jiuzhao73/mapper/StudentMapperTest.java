package com.jiuzhao73.mapper;

import com.jiuzhao73.pojo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void createStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
        Student student = new Student("1000000000", "", "久曌", "男", 1, 102, 2, "住宿", "18350000000");
        System.out.println(studentMapper.updateStudent(student));
    }

    @Test
    void queryStudent() {
    }

    @Test
    void queryStudentBySid() {

    }

    @Test
    void queryStudentBySidPwd() {
        Student student = new Student("1000000001", "123456", "", "", 1, 1, 1, "", "");
        System.out.println(studentMapper.queryStudentNumBySidPwd(student));
    }

}
