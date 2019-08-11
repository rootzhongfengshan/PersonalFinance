package net.vv2.util;

//import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParseExcelDataUtil {
	
	protected String[]  selectkeyfromdbcfg(String str){
		Map<String, String> map=new HashMap<String, String>();
		map.put("keyname",str);
		List<Map<String, String>> resultType=new ArrayList<Map<String, String>>();
		//List<Map> resultType =EasyDataFatcherOnIbatis.queryBySqlKey("getsynckeycfg",false,map);
		String keys = (String)resultType.get(0).get("KEYS");
		//库中存数据的时候不要存双引号
		//String tmp=keys.replaceAll("\"","");
		String [] procArray = keys.split(",");
		return procArray;	
	}
	
	public static String[]  gettablekeycfg(String str){
		Map<String,String[]> map=new HashMap<String,String[]>();
		final String [] keys = {"businessid","orderid","createtime","paytime","modifytime","source","type","partner","shopname","fee","kind","status","servicefee","refund","remarks","fundstatus"};
		map.put(str, keys);
		return map.get(str);	
	}
	
	
	
	public static List<Map<String,Object>> parseExcelFile(File file, String[] cellKeys) throws Exception
    {
		List<Map<String,Object>> objList = parseExcelFile(new FileInputStream(file), cellKeys);
        return objList;
    }
	
    /**
     * 根据Excel生成对应的对象
     *
     * @param inputStream     上传文件流
     * @param cellKeys 对应Excel的行数据,每行对应到对象的Field值
     * @return
     * @throws Exception
     */
    public static List<Map<String,Object>> parseExcelFile(InputStream inputStream,String[] cellKeys)throws Exception
    {
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        if (null == inputStream)
        {
            throw new RuntimeException("Empty file!");
        }
        if (null == cellKeys || cellKeys.length == 0)
        {
            throw new RuntimeException("Cellkeys can't be null");
        }
        Workbook wb = null;
        try
        {
            // 构造Workbook（工作薄）对
            wb = WorkbookFactory.create(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        List<Map<String,Object>> objList = new ArrayList<Map<String,Object>>();
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);
            if (row != null)
            {
                int lastCellNum = Math.min(row.getLastCellNum(), cellKeys.length);
                Cell cell = null;
                Map<String,Object> map = new HashMap<String,Object>();
                for (int j = 0; j < lastCellNum; j++)
                {
                    cell = row.getCell(j);
                    if (cell != null)
                    {
                        map.put(cellKeys[j], getCellValue(cell));
                    }
                }
                objList.add(map);
            }
        }
        return objList;
    }
	
    
    public static String getCellValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    short format = cell.getCellStyle().getDataFormat();
                    if(format == 14 || format == 31 || format == 57 || format == 58){ 	//excel中的时间格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellvalue = sdf.format(date);
                    }
                    // 判断当前的cell是否为Date
                    else if (HSSFDateUtil.isCellDateFormatted(cell)) {  //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
                        // 如果是Date类型则，取得该Cell的Date值           // 对2014-02-02格式识别不出是日期格式
                        Date date = cell.getDateCellValue();
                        cellvalue = com.xiaoleilu.hutool.date.DateUtil.format(date, "yyyy-MM-dd");
                    } else { // 如果是纯数字
                        // 取得当前Cell的数值
                        cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());

                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getStringCellValue().replaceAll("'", "''").trim();
                    break;
                case  HSSFCell.CELL_TYPE_BLANK:
                    cellvalue = null;
                    break;
                // 默认的Cell值
                default:{
                    cellvalue = "";
                }
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

	
	public List<Map<String,String>> ParseExcelDataFromXlsFile(File file) throws Exception {
		return null;
	}

}
