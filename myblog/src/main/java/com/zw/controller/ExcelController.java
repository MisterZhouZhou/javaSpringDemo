package com.zw.controller;

import com.zw.excel.ReportExcel;
import com.zw.model.Product;
import com.zw.response.ResultBody;
import com.zw.service.ProductService;
import com.zw.utils.TransCodingUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private ProductService productService;

    /**
     * 导出产品excel
     * @param request
     * @return
     */
    @GetMapping(value = "/exportProduct")
    public String exportProduct(HttpServletResponse response,HttpServletRequest request){

        List<Product> products = productService.findProducts();
        ReportExcel reportExcel = new ReportExcel();
        try {
            reportExcel.excelExport(products,"测试",Product.class,0,response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 分页获得产品信息
     * @param request
     * @return
     */
    @ApiOperation(value="分页获得产品信息", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/getProducts")
    public ResultBody getProducts(HttpServletRequest request){
        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        JSONObject jsonObject = productService.findProducts(rows, pageNum);
        return new ResultBody(jsonObject);
    }


    /**
     * 添加产品信息
     * @param products 产品信息列表
     * @return
     */
    @PostMapping(value = "/addProduct")
    public List<Product> addProduct(@RequestBody List<Product> products){
        for (Product product : products){
            productService.insert(product);
        }
        return products;
    }


}
