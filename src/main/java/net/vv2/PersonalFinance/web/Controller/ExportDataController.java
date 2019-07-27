package net.vv2.PersonalFinance.web.Controller;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.PersonalFinance.domain.Cost;
import net.vv2.PersonalFinance.domain.Income;
import net.vv2.PersonalFinance.domain.Property;
import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.PersonalFinance.service.impl.IncomeServiceImpl;
import net.vv2.PersonalFinance.service.impl.PropertyServiceImpl;
import net.vv2.baby.domain.User;
import net.vv2.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance/exportdata")
public class ExportDataController {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;

    List<Income> list;


    @RequestMapping("index")
    public String index(Model model){
        return "PersonalFinance/showdata/IncomeShow";
    }

    @RequestMapping("showIncome")
    public String gotosaveIncome(Model model){
        return "PersonalFinance/showdata/IncomeShow";
    }

    @RequestMapping("showProperty")
    public String gotosaveProperty(Model model){
        return "PersonalFinance/showdata/PropertyShow";
    }

    @RequestMapping("showCost")
    public String gotosaveCost(Model model){
        return "PersonalFinance/showdata/CostShow";
    }

    @RequestMapping("exportIncomeList")
    public void postItemExcel(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {

        //导出excel
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        String[] headers = { "月份", "收款细节类别", "收款金额", "收款日期","备注"};
        fieldMap.put("months", "月份");
        fieldMap.put("detail", "收款细节类别");
        fieldMap.put("rec_amount", "收款金额");
        fieldMap.put("rec_date", "收款日期");
        fieldMap.put("remarks", "备注");
        String sheetName = "收入管理报表";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=ItemManage.xls");//默认Excel名称
        response.flushBuffer();
        OutputStream fos = response.getOutputStream();
        try {
            ExcelUtil.listToExcel(list, fieldMap, sheetName, fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("exportIncomeList22")
    public String ShowIncomeList(String start_date ,String end_date,HttpServletResponse response,
                                 HttpSession session,Model model) throws IOException {
        List<Income> list =incomeService.selectIncomeByDate(start_date,end_date);
        model.addAttribute("list",list);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName1 = "income_report"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "月份", "收款细节类别", "收款金额", "收款日期","备注"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Income income : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(income.getMonths());
            row1.createCell(1).setCellValue(income.getDetail());
            row1.createCell(2).setCellValue(income.getRec_amount().toString());
            row1.createCell(3).setCellValue(income.getRec_date().toString());
            row1.createCell(4).setCellValue(income.getRemarks());
            rowNum++;
        }
        response.setHeader("Content-type","application/vnd.ms-excel");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName1);
        response.flushBuffer();
        response.getOutputStream().flush();
        workbook.write(response.getOutputStream());
        response.getOutputStream().close();
        return "PersonalFinance/showdata/IncomeShowList";
    }

    @RequestMapping("exportIncomeListall")
    public void exportIncomeListall(HttpServletResponse response, Model model) throws IOException {
        List<Income> list =incomeService.selectAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName1 = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "月份", "收款细节类别", "收款金额", "收款日期","备注"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Income income : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(income.getMonths());
            row1.createCell(1).setCellValue(income.getDetail());
            row1.createCell(2).setCellValue(income.getRec_amount().toString());
            row1.createCell(3).setCellValue(income.getRec_date().toString());
            row1.createCell(4).setCellValue(income.getRemarks());
            rowNum++;
        }
        response.setHeader("Content-type","application/vnd.ms-excel");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName1);
        response.flushBuffer();
        workbook.write(response.getOutputStream());



        /*String fileName="票据报表";
        try {
            response.setHeader("Content-type","application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+".xls");
            // 模板导出Excel
            templateExport(response.getOutputStream(), exportService.loadBillList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        model.addAttribute("list",list);
        return "PersonalFinance/showdata/IncomeShowList";*/
    }

    @RequestMapping("exportIncomeList2")
    public void exportIncomeList(HttpServletResponse response, HttpServletRequest request,  Model model) throws IOException {
        List<Income> list =incomeService.selectAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName1 = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "月份", "收款细节类别", "收款金额", "收款日期","备注"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Income income : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(income.getMonths());
            row1.createCell(1).setCellValue(income.getDetail());
            row1.createCell(2).setCellValue(income.getRec_amount().toString());
            row1.createCell(3).setCellValue(income.getRec_date().toString());
            row1.createCell(4).setCellValue(income.getRemarks());
            rowNum++;
        }
        response.setHeader("Content-type","application/vnd.ms-excel");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName1);
        response.flushBuffer();
        workbook.write(response.getOutputStream());



        /*String fileName="票据报表";
        try {
            response.setHeader("Content-type","application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+".xls");
            // 模板导出Excel
            templateExport(response.getOutputStream(), exportService.loadBillList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        model.addAttribute("list",list);
        return "PersonalFinance/showdata/IncomeShowList";*/
    }

    @RequestMapping("ShowCostList")
    public String ShowCostList(String start_date ,String end_date,
                                 HttpSession session,Model model){
        List<Cost> list =costService.selectCostByDate(start_date,end_date);
        model.addAttribute("list",list);
        return "PersonalFinance/showdata/CostShowList";
    }

    @RequestMapping("ShowPropertyList")
    public String ShowPropertyList(String start_date ,String end_date,
                               HttpSession session,Model model){
        List<Property> list = propertyService.selectPropertyByDate(start_date,end_date);
        model.addAttribute("list",list);
        return "PersonalFinance/showdata/PropertyShowList";
    }


    /**
     *  保存身高体重数据
     *  @param months
     * @param months
     * @param detail
     * @param rec_amount
     * @param rec_date
     * @param remarks
     * @param session
     * @param mv
     * @return int
     */
    @RequestMapping("record/saveIncome")
    public ModelAndView saveIncome(String months, String detail, Float rec_amount, String rec_date, String remarks,
                                 HttpSession session,
                                 ModelAndView mv){
        System.out.println(" 准备收款记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Income income = new Income(months, detail,  rec_amount,  DateUtil.parse(rec_date), remarks);//准备需要添加的身高体重数据
        System.out.println(income);
        //保存数据，判断或失败，并进行相关的跳转
        if (incomeService.insertIncome(income)>0){
            String msg = "收入数据添加成功！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveIncome_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "收入数据添加失败！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveIncome_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping("record/saveProperty")
    public ModelAndView saveProperty(String record_date,Float wechat1,Float wechat2,Float alipay1,Float alipay2,
                                     Float zhaoshang,Float jiaotong,Float jianshe,Float beijing,Float maomingyouzheng,
                                     Float beijingyouzheng,Float gongshang,Float all_sum,String remarks,
                                   HttpSession session,
                                   ModelAndView mv){
        System.out.println("准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        all_sum=wechat1+wechat2+alipay1+alipay2+zhaoshang+jiaotong+jianshe+beijing+maomingyouzheng+beijingyouzheng+gongshang;
        Date nowtime = new Date();//创建当前时间
        Property property = new Property(DateUtil.parse(record_date),wechat1,wechat2,alipay1,alipay2,zhaoshang,jiaotong,jianshe,beijing,maomingyouzheng,beijingyouzheng,gongshang,all_sum,remarks);//准备需要添加的身高体重数据
        System.out.println(property);
        //保存数据，判断或失败，并进行相关的跳转
        if (propertyService.insertProperty(property)>0){
            String msg = "数据添加成功！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveProperty_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "数据添加失败！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveProperty_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping("record/saveCost")
    public ModelAndView saveCost(String consume_date ,String consume_name,Float project_fee,Float consume_fee,String consume_type,String consume_category,String pay_way,String remarks,
                                   HttpSession session,
                                   ModelAndView mv){
        System.out.println("准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Cost cost = new Cost(DateUtil.parse(consume_date),consume_name,project_fee,consume_fee,consume_type,consume_category,pay_way,remarks);//准备需要添加的身高体重数据
        System.out.println(cost);
        //保存数据，判断或失败，并进行相关的跳转
        if (costService.insertCost(cost)>0){
            String msg = " 添加成功";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveCost_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = " 添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveCost_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }


    @RequestMapping("analyseIncome")
    public ModelAndView analyseIncome(String start_date ,String end_date,
                                 HttpSession session,
                                 ModelAndView mv){
        System.out.println(" 准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Float sum_recamount=incomeService.selectSumIncomeByDate(start_date,end_date); ;//准备需要添加的身高体重数据
        System.out.println(sum_recamount);
        //保存数据，判断或失败，并进行相关的跳转
        if (!(null==sum_recamount||sum_recamount==0)){
            String msg = " 添加成功,共收入"+sum_recamount;
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/#tab1\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = " 添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse#tab1\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }



    @RequestMapping("analyseall")
    public ModelAndView analyseall(String start_date ,String end_date,
                                      HttpSession session,
                                      ModelAndView mv){
        System.out.println("准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Float sum_recamount=incomeService.selectSumIncomeByDate(start_date,end_date);
        Float sum_costamount=costService.selectSumCostByDate(start_date,end_date);
        Float start_propertyamount=propertyService.selectSumpropertyByDate(start_date);
        Float end_propertyamount=propertyService.selectSumpropertyByDate(end_date);
        Float subtract_property=(start_propertyamount+sum_recamount)-end_propertyamount;
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(start_date);
        stringBuffer.append("至");
        stringBuffer.append(end_date);
        stringBuffer.append("期间收入:");
        stringBuffer.append(sum_recamount);
        stringBuffer.append("共花费:");
        stringBuffer.append(sum_costamount);
        stringBuffer.append("\n");
        stringBuffer.append("上月（");
        stringBuffer.append(start_date);
        stringBuffer.append("）各账户余额为:");
        stringBuffer.append(start_propertyamount);
        stringBuffer.append("本月（");
        stringBuffer.append(end_date);
        stringBuffer.append("）各账户余额为:");
        stringBuffer.append(end_propertyamount);
        stringBuffer.append("，本月花费:");
        stringBuffer.append(subtract_property);
        stringBuffer.append("，与Cost花费记录");
        if ((sum_costamount-subtract_property>10)||(subtract_property-sum_costamount>10)){
            stringBuffer.append("不相符，请分析原因");
        }else{
            stringBuffer.append("相符。");
        }
        System.out.println(stringBuffer.toString());
        //保存数据，判断或失败，并进行相关的跳转
        if (!(null==sum_recamount||sum_recamount==0)){
            String msg = "添加成功,共收入"+sum_recamount;
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/#tab1\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse#tab1\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }



}
