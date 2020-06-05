package net.vv2.personal.finance.web.controller;

import net.vv2.personal.finance.constants.Constants;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.IncomeServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import net.vv2.system.service.TSysConfigMessageService;
import net.vv2.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance/exportData")
public class ExportDataController {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;
    @Value("${upload.quotaFile}")
    private String Quota_FILE_PATH;
    @Autowired
    private TSysConfigMessageService myTSysConfigMessageService;

    @RequestMapping("exportCostList")
    public void exportCostList(String start_date, String end_date, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<String> excelHead = myTSysConfigMessageService.getConfigValueList(Constants.EXCEL_HEAD, Constants.COST);
        List<String> excelHeadKey = myTSysConfigMessageService.getConfigValueList(Constants.EXCEL_HEAD_KEY, Constants.COST);
        List<Map<String, Object>> list = costService.selectCostByDateReturnMap(start_date, end_date);
        Workbook wb = HandleExcelUtils.Excel2007AboveOperate3(excelHead, excelHeadKey, list);
        String fileName = myTSysConfigMessageService.getConfigValue(Constants.FILENAME, Constants.COST);
        fileName = fileName + start_date + "至" + end_date + Constants.EXCEL_FILE_SUFFIX;
        fileName = ExportUtil.codeFileName(request, fileName);
        HandleFileUtils.createExcelFileOnTheServer(response, Quota_FILE_PATH, fileName, wb);
        HandleFileUtils.DownExcelTemplate(response, Quota_FILE_PATH, fileName);
        HandleFileUtils.deleteFile(Quota_FILE_PATH, fileName);

    }

    @RequestMapping("exportIncomeList")
    public void exportIncomeList(String start_date, String end_date, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<String> excelHead = myTSysConfigMessageService.getConfigValueList(Constants.EXCEL_HEAD, Constants.INCOME);
        List<String> excelHeadKey = myTSysConfigMessageService.getConfigValueList(Constants.EXCEL_HEAD_KEY, Constants.INCOME);
        List<Map<String, Object>> list = incomeService.selectIncomeByDateReturnMap(start_date, end_date);
        Workbook wb = HandleExcelUtils.Excel2007AboveOperate3(excelHead, excelHeadKey, list);
        String fileName = myTSysConfigMessageService.getConfigValue(Constants.FILENAME, Constants.INCOME);
        fileName = fileName + start_date + "至" + end_date + Constants.EXCEL_FILE_SUFFIX;
        fileName = ExportUtil.codeFileName(request, fileName);
        HandleFileUtils.createExcelFileOnTheServer(response, Quota_FILE_PATH, fileName, wb);
        HandleFileUtils.DownExcelTemplate(response, Quota_FILE_PATH, fileName);
        HandleFileUtils.deleteFile(Quota_FILE_PATH, fileName);
    }

    @RequestMapping("exportPropertyList")
    public void exportPropertyList(String start_date, String end_date, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<String> excelHead = myTSysConfigMessageService.getConfigValueList(Constants.EXCEL_HEAD, Constants.PROPERTY);
        List<String> excelHeadKey = myTSysConfigMessageService.getConfigValueList(Constants.EXCEL_HEAD_KEY, Constants.PROPERTY);
        List<Map<String, Object>> list = propertyService.selectPropertyByDateReturnMap(start_date, end_date);
        Workbook wb = HandleExcelUtils.Excel2007AboveOperate3(excelHead, excelHeadKey, list);
        String fileName = myTSysConfigMessageService.getConfigValue(Constants.FILENAME, Constants.PROPERTY);
        fileName = fileName + start_date + "至" + end_date + Constants.EXCEL_FILE_SUFFIX;
        fileName = ExportUtil.codeFileName(request, fileName);
        HandleFileUtils.createExcelFileOnTheServer(response, Quota_FILE_PATH, fileName, wb);
        HandleFileUtils.DownExcelTemplate(response, Quota_FILE_PATH, fileName);
        HandleFileUtils.deleteFile(Quota_FILE_PATH, fileName);
    }






}
