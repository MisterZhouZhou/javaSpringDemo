package com.zw.controller;

import com.zw.excel.ReportExcel;
import com.zw.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @GetMapping(value = "/exportProduct")
    public String exportProduct(HttpServletResponse response,HttpServletRequest request){

        List<Product> list = new ArrayList<Product>();

        for (int i = 0 ; i<100 ; i++) {
            //组装测试数据
            Product product = new Product();
            product.setName("爱奇艺会员"+i);
            product.setPrice(9.99);
            product.setDate(new Date());
            list.add(product);
        }

        ReportExcel reportExcel = new ReportExcel();
        try {
            reportExcel.excelExport(list,"测试",Product.class,0,response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
