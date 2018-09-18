package com.zw.kotlinjpa.controller

import com.zw.kotlinjpa.model.Student
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentController{
    @RequestMapping("/findByName")
    fun findByName(name: String): List<Student> {
        val student = Student(id = 11, name = "zw1")
        val student2 = Student(id = 22, name = "zw2")
        var list: Array<Student> = arrayOf(student, student2);
        return list.toList()
    }

    @RequestMapping(value = "/add", method = [RequestMethod.GET])
    fun add(name:String):String{
        val student=Student(id=null,name = name)
        return "success"
    }

    @RequestMapping(value = "home", method = [RequestMethod.GET])
    fun home():List<Student>{
        val student = Student(id = 11, name = "zw1")
        var list: Array<Student> = arrayOf(student);
        return list.toList()
    }

}