package com.zw.springdemo.service;

import com.zw.springdemo.entity.Student;

import java.util.List;

public interface StudentService {
     /**
     * @Description: 获取学生列表
     * @param: user
     * @return:
     */
     List<Student> list();

     /**
     * @Description: 根据name,num获取学生信息
     * @param: user
     * @return:
     */
    Student findStudentWithNumAndName(String name, Integer num);
}
