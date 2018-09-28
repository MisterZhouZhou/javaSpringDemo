package com.zw.learning.controller;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String excel() {
        return "excel";
    }

    @SuppressWarnings("resource")
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public void importExcel(@RequestParam("file")MultipartFile file) throws Exception{
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        // 将文件存储到static/excel目录下
        byte[] bytes = file.getBytes();
        // 获取时间戳
        Long currentTimeMillis = System.currentTimeMillis();
        Path path = Paths.get("/Users/zhouwei/Desktop/zw/java/learning/src/main/resources/static/excel/" +file.getOriginalFilename());
        // 将上传的文件写入文件
        Files.write(path,bytes);

        // 读取文件
        File filePath = new File(path.toString());
        FileInputStream fileInputStream = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            int id = (int) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            int age = (int) row.getCell(2).getNumericCellValue();
            String address = row.getCell(3).getStringCellValue();
            System.out.println(id + "-" + name + "-" + age+ "-" + address);
        }
        // 关闭文件
        fileInputStream.close();
        System.out.println("测试完成了");

    }


    @SuppressWarnings("resource")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {
        String[] tableHeaders = {"id", "姓名", "年龄"};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        // 设置cell样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);

        // 设置字体样式
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setBold(true);
        cellStyle.setFont(font);

        // 将第一行的三个单元格给合并
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        HSSFCell beginCell = row.createCell(0);
        beginCell.setCellValue("通讯录");
        beginCell.setCellStyle(cellStyle);

        row = sheet.createRow(1);
        // 创建表头
        for (int i = 0; i < tableHeaders.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(tableHeaders[i]);
            cell.setCellStyle(cellStyle);
        }

        List<Map> users = new ArrayList<Map>();
        Map map1 = new HashMap();
        map1.put("id", 1L);
        map1.put("name", "张三");
        map1.put("age", 21);
        users.add(map1);
        Map map2 = new HashMap();
        map2.put("id", 2L);
        map2.put("name", "李四");
        map2.put("age", 22);
        users.add(map2);
        Map map3 = new HashMap();
        map3.put("id", 3L);
        map3.put("name", "王五");
        map3.put("age", 23);
        users.add(map3);

        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 2);
            Map user = (Map)users.get(i);
            long id = (long)user.get("id");
            String name = (String)user.get("name");
            int age = (int)user.get("age");

            HSSFCellStyle cellStyle_cell = workbook.createCellStyle();
            cellStyle_cell.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle_cell.setVerticalAlignment(CellStyle.ALIGN_CENTER);

            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(id);
            cell0.setCellStyle(cellStyle_cell);

            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(name);
            cell1.setCellStyle(cellStyle_cell);

            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(age);
            cell2.setCellStyle(cellStyle_cell);
        }

        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=template.xls");

        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

}
