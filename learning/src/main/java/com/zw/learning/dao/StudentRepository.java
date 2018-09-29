package com.zw.learning.dao;

import com.zw.learning.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByStuNameAndStuNum(String name, Integer num);
}
