package com.zw.learning.entity;

import javax.persistence.*;

/**
  * 学生基本信息实体
  * @ClassName: StudentInfoBean
  * @author sunt
  * @version V1.0
  */
@Entity
@Table(name = "t_student")
public class Student {
    /**
      * 学号,唯一不重复
      * 声明主键
      * 声明主键的生成策略
     **/
    @Id
    @GeneratedValue
    private Integer stuNum;

    @Column(length = 45)
    private String stuName;

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
