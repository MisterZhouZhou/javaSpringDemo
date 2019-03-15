package com.zw.model;

import com.zw.excel.ExcelAnnotation;

import java.util.Date;

public class Product {

    @ExcelAnnotation(id=1,name={"产品名称","商品名称"},width = 5000)
    private String name;
    @ExcelAnnotation(id=2,name={"产品价格","商品价格"},width = 5000)
    private double price;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
