package com.zw.learning.service.impl;

import com.zw.learning.dao.StudentRepository;
import com.zw.learning.entity.Student;
import com.zw.learning.service.StudentService;
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
