package com.zw.springdemo.service.impl;

import com.zw.springdemo.dao.StudentRepository;
import com.zw.springdemo.entity.Student;
import com.zw.springdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> list() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentWithNumAndName(String name, Integer num) {
        return studentRepository.findByStuNameAndStuNum(name, num);
    }


}
