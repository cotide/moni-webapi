package com.gold.moni.helper.excel;

import com.gold.moni.helper.common.extend.String2Utf8;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 *Excel表格生成工具类
 **/
public class ExcelsUtils {

    public static void createTable(
            List<String> object,
            Class c,
            String tablename,
            String[] cellnames,
            HttpServletResponse response) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {


        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet(tablename);
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue(tablename);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue(cellnames[0]);
        //遍历传入的每个对象的所有类成员属性值并且设置到Excel的单元格里
        int i = 0;
        for (String item :object)
        {
            ++i;
            HSSFRow  row=sheet.createRow(i);
            try {
                row.createCell(0).setCellValue(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.setCharacterEncoding("UTF-8");
        response.reset();
        //调用工具类保证导出时中文表名不会出现乱码现象
        response.setHeader("Content-disposition", "attachment; filename="+ String2Utf8.Utf8String(tablename+".xls"));
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
}
