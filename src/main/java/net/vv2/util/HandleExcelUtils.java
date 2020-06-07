package net.vv2.util;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HandleExcelUtils {


    public static Workbook Excel2007AboveOperate() throws IOException {
        XSSFWorkbook workbook1 = new XSSFWorkbook();
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
        Sheet first = sxssfWorkbook.createSheet();
        for (int i = 0; i < 100000; i++) {
            Row row = first.createRow(i);
            for (int j = 0; j < 11; j++) {
                if (i == 0) {
                    // 首行
                    row.createCell(j).setCellValue("column" + j);
                } else {
                    // 数据
                    if (j == 0) {
                        CellUtil.createCell(row, j, String.valueOf(i));
                    } else {
                        CellUtil.createCell(row, j, String.valueOf(Math.random()));
                    }
                }
            }
        }
        return workbook1;
    }

    public static Workbook Excel2007AboveOperate2() throws IOException {
        XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File("J3-684b37f5-d182-4cf2-ab43-27b3dce9cc9d.xlsx")));
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
        Sheet first = sxssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 100; i++) {
            Row row = first.createRow(i);
            for (int j = 0; j < 11; j++) {
                if (i == 0) {
                    // 首行
                    row.createCell(j).setCellValue("column" + j);
                } else {
                    // 数据
                    if (j == 0) {
                        CellUtil.createCell(row, j, String.valueOf(i));
                    } else {
                        CellUtil.createCell(row, j, String.valueOf(Math.random()));
                    }
                }
            }
        }
        return sxssfWorkbook;
    }

/*    public static Workbook Excel2007AboveOperate3(List<String> excelHead, List<String> excelHeadKey, List<JSONObject> currentPageList) {
        XSSFWorkbook wb = new XSSFWorkbook();
        wb = createExcel(wb, excelHead, excelHeadKey, currentPageList);
        return wb;
    }*/

    public static Workbook Excel2007AboveOperate3(List<String> excelHead, List<String> excelHeadKey, List<Map<String, Object>> currentPageList) {
        XSSFWorkbook wb = new XSSFWorkbook();
        wb = createExcel(wb, excelHead, excelHeadKey, currentPageList);
        return wb;
    }

    private static XSSFWorkbook createExcel(XSSFWorkbook wb, List<String> excelHead, List<String> excelHeadKey, List<Map<String, Object>> currentPageList) {
        CellStyle style = setStyle(wb);
        CellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        //sheet1处理
        Sheet sheet1 = wb.createSheet("00000000");
        createSheetHead(sheet1, excelHead, 128 * 50, style);
        createSheetDataByListMap(sheet1, excelHeadKey, currentPageList);
        return wb;
    }

/*    private static XSSFWorkbook createExcel(XSSFWorkbook wb, List<String> excelHead, List<String> excelHeadKey, List<JSONObject> currentPageList) {
        CellStyle style = setStyle(wb);
        CellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        //sheet1处理
        Sheet sheet1 = wb.createSheet("00000000");
        createSheetHead(sheet1, excelHead, 128 * 50, style);
        createSheetData(sheet1, excelHeadKey, currentPageList);
        return wb;
    }*/


    public static CellStyle setStyle(XSSFWorkbook wb) {
        //设置标题格式
        CellStyle style = wb.createCellStyle();
        //设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //前景填充色
        style.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    //创建表头
    public static void createSheetHead(Sheet sheet, List<String> excelHead, int width, CellStyle style) {
        Row firstRow = sheet.createRow(0);
        int excelHeadSize = excelHead.size();
        for (int i = 0; i < excelHeadSize; i++) {
            sheet.setColumnWidth(i, width);
            Cell cell = firstRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(excelHead.get(i));
        }
    }


/*
    public static void createSheetData(Sheet sheet, List<String> excelHead, List<JSONObject> currentPageList) {
        int size = currentPageList.size();
        int excelHeadSize = excelHead.size();
        for (int i = 0; i < size; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < excelHeadSize; j++) {
                String keyName = excelHead.get(j);
                CellUtil.createCell(row, j, currentPageList.get(i).getString(keyName));
            }

        }
    }
*/

    public static void createSheetDataByListMap(Sheet sheet, List<String> excelHead, List<Map<String, Object>> currentPageList) {
        int size = currentPageList.size();
        int excelHeadSize = excelHead.size();
        for (int i = 0; i < size; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < excelHeadSize; j++) {
                String keyName = excelHead.get(j);
                CellUtil.createCell(row, j, String.valueOf(currentPageList.get(i).get(keyName)));
            }

        }
    }
}
