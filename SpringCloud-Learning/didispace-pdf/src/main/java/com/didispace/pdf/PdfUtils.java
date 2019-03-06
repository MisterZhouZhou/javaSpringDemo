package com.didispace.pdf;

import com.didispace.entity.LoanEntity;
import com.didispace.entity.TitleEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * PDF工具
 */
public class PdfUtils {
    /**
     * 生成
     * @param pdfName pdfname

     * @return
     */
    public static String turnToPdf(String pdfName) {
        String path = "";
        try {
            path = "./didispace-pdf/"+pdfName+".pdf";
            Document document = new Document();
            FileOutputStream out = new FileOutputStream(path);
            PdfWriter writer  = PdfWriter.getInstance(document, out);
            // 打开文件
            document.open();

            //中文字体,解决中文不能显示问题
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese);//正常字体
            Font fontBigBold = new Font(bfChinese, 20, Font.BOLD);//加粗大字体

            //添加主题
            Paragraph theme = new Paragraph("还款计划表", fontBigBold);
            theme.setAlignment(Element.ALIGN_CENTER);
            document.add(theme);

            //借款信息
            Paragraph peopleInfo = new Paragraph("借款人："+"zw"+"    "+"借款金额："+"100"+"万元", font);
            peopleInfo.setAlignment(Element.ALIGN_CENTER);
            document.add(peopleInfo);

            //  创建table
            TitleEntity titleEntity = new TitleEntity();
            titleEntity.setNumberTitle("期数");
            titleEntity.setDateTitle("日期");
            titleEntity.setLoanMoneyTitle("金额（元）");

            // 添加数据
            List rows = new ArrayList();
            for (int i=0;i<3;i++){
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setLoanNumber(1);
                loanEntity.setDate(format.format(new Date()));
                loanEntity.setLoanMoney(1000+i);
                rows.add(loanEntity);
            }

            PdfUtils.createTable(document,3, titleEntity, rows);

            // 关闭文档
            document.close();
            // 关闭书写器
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }


    // 创建table
    public static void  createTable(Document document, int numColumns, TitleEntity titleEntity, List<Object> rows) {
        try{
            //TODO Table1
            PdfPTable table = new PdfPTable(numColumns);
            table.setWidthPercentage(90); // 宽度100%填充
            table.setSpacingBefore(20f); // 前间距
            table.setSpacingAfter(20f); // 后间距

            List<PdfPRow> listRow = table.getRows();
            //设置列宽
            float[] columnWidths = {1f, 2f, 3f};
            table.setWidths(columnWidths);

            // 添加title数据
            PdfUtils.createTableRow(titleEntity, listRow, true);

            // 添加row数据
            for (Object model : rows){
                 PdfUtils.createTableRow(model, listRow, false);
            }

            //把表格添加到文件中
            document.add(table);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 创建table Row
    public static void createTableRow(Object model, List<PdfPRow> listRow, boolean showBgColor){

        // 获取属性
        Field[] fields = model.getClass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<Field>();
        for (Field fie : fields) {
            fieldList.add(fie);
        }

        // 获取列数
        int columnsize = fieldList.size();
        PdfPCell cells[] = new PdfPCell[columnsize];
        PdfPRow row = new PdfPRow(cells);

        // 获取该类 并获取自身方法
        Class clazz = model.getClass();
        for (int i = 0; i < columnsize; i++) {
            Field field = fieldList.get(i);
            // 获取方法名
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
            try{
                //中文字体,解决中文不能显示问题
                BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                Font font = new Font(bfChinese);//正常字体
                Font fontBold = new Font(bfChinese, 12, Font.BOLD);//正常加粗字体
                Font fontBig = new Font(bfChinese, 20);//大字体
                Font fontBigBold = new Font(bfChinese, 20, Font.BOLD);//加粗大字体

                // 获取getter method
                Method method = clazz.getMethod(methodName);
                // 获取该字段的注解对象
                Object result = method.invoke(model);

                cells[i] = new PdfPCell(new Paragraph(String.valueOf(result), fontBold));//单元格内容
                cells[i].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[i].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                cells[i].setFixedHeight(25f);
                if(showBgColor){
                    cells[i].setBackgroundColor(BaseColor.LIGHT_GRAY);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        listRow.add(row);
    }

}
