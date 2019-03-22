package com.zw.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zw.excel.ExcelAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Product {

    @ExcelAnnotation(id=1,name={"产品名称","商品名称"},width = 5000)
    private String name;

    @ExcelAnnotation(id=2,name={"产品价格","商品价格"},width = 5000)
    private double price;

//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")   //格式化前台日期参数注解
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") //Jackson包使用注解
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @ExcelAnnotation(id=3,name={"生产日期"},width = 5000)
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
