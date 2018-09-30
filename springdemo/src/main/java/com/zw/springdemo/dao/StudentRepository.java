package com.zw.springdemo.dao;

import com.zw.springdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByStuNameAndStuNum(String name, Integer num);
}
