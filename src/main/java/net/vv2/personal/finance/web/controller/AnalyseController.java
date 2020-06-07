package net.vv2.personal.finance.web.controller;

import net.vv2.personal.finance.domain.Cost;
import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.service.IncomeService;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import net.vv2.system.domain.User;
import net.vv2.util.HandleDateTools;
import net.vv2.util.TestPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance/analyse")
public class AnalyseController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;


    @RequestMapping("analyseCostByTypeIndex1")
    public String index(Model model){
        return "PersonalFinance/analyse/analyseCostByType";
    }
    @RequestMapping("analyse")
    public String returnanalyse(Model model){
        return "PersonalFinance/analyse/Analyse";
    }


    @RequestMapping("analyseIncome")
    public ModelAndView analyseIncome(String start_date ,String end_date,
                                 HttpSession session,
                                 ModelAndView mv){
        System.out.println(" 准备记录-----------");
        User user = (User) session.getAttribute("user");
        Date nowtime = new Date();
        Float sum_recamount = incomeService.selectSumIncomeByDate(start_date, end_date);
        ;
        System.out.println(sum_recamount);
        //保存数据，判断或失败，并进行相关的跳转
        if (!(null==sum_recamount||sum_recamount==0)){
            String msg = "共收入"+sum_recamount;
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
                                      ModelAndView mv) throws IOException {

        User user = (User) session.getAttribute("user");
        Date nowtime = new Date();
        Float sum_recamount=incomeService.selectSumIncomeByDate(start_date,end_date);
        Float sum_costamount=costService.selectSumCostByDate(start_date,end_date);
        Float start_propertyamount=propertyService.selectSumpropertyByDate(start_date);
        Float end_propertyamount=propertyService.selectSumpropertyByDate(end_date);
        Float subtract_property=(start_propertyamount+sum_recamount)-end_propertyamount;
        Float diffcost=sum_costamount-subtract_property;
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(start_date);
        stringBuffer.append("至");
        stringBuffer.append(end_date);
        stringBuffer.append("期间总共收入:");
        stringBuffer.append(sum_recamount);
        stringBuffer.append("\n");
        stringBuffer.append("总共花费:");
        stringBuffer.append(sum_costamount);
        stringBuffer.append("\n");
        stringBuffer.append("上月（");
        stringBuffer.append(start_date);
        stringBuffer.append("）各账户余额为:");
        stringBuffer.append(start_propertyamount);
        stringBuffer.append("\n");
        stringBuffer.append("本月（");
        stringBuffer.append(end_date);
        stringBuffer.append("）各账户余额为:");
        stringBuffer.append(end_propertyamount);
        stringBuffer.append("，\n本月花费:");
        stringBuffer.append(subtract_property);
        stringBuffer.append("，\n与Cost花费记录,相差");
        stringBuffer.append(diffcost);
        if ((diffcost>10)||((-1)*diffcost>10)){
            stringBuffer.append("不相符，请分析原因\n");
        }else{
            stringBuffer.append("相符。\n");
        }
        System.out.println(stringBuffer.toString());
        String filename=start_date+"-"+end_date+"-"+"analyseall.pdf";
        TestPdf.createPDFWithString(filename,filename,stringBuffer.toString());
        //保存数据，判断或失败，并进行相关的跳转
        if (!(null==sum_recamount||sum_recamount==0)){
            String msg = "添加成功,共收入"+sum_recamount;
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/analyse\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/analyse\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping(value = {"analyseCostByType", "analyseCostByTypeIndex"})
    public String gotoPageAnalyseCostByType(String start_date, String end_date, Model model) {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<Cost> costList = costService.selectCostByDate(start_date, end_date);
        List<Map<String, String>> costListGroupByConsumeType = costService.queryCostListByDateGroupByConsumeType(start_date, end_date);
        List<String> xAxisDataGroupByConsumeType = costListGroupByConsumeType.stream().map(s -> s.get("consume_type")).collect(Collectors.toList());
        List<String> seriesDataGroupByConsumeType = costListGroupByConsumeType.stream().map(s -> s.get("consume_fee")).collect(Collectors.toList());
        List<Map<String, String>> costListGroupByConsumeCategory = costService.queryCostListByDateGroupByConsumeCategory(start_date, end_date);
        List<String> xAxisDataGroupByConsumeCategory = costListGroupByConsumeCategory.stream().map(s -> s.get("consume_category")).collect(Collectors.toList());
        List<String> seriesDataGroupByConsumeCategory = costListGroupByConsumeCategory.stream().map(s -> s.get("consume_fee")).collect(Collectors.toList());
        model.addAttribute("xAxisDataGroupByConsumeType", xAxisDataGroupByConsumeType);
        model.addAttribute("seriesDataGroupByConsumeType", seriesDataGroupByConsumeType);
        model.addAttribute("xAxisDataGroupByConsumeCategory", xAxisDataGroupByConsumeCategory);
        model.addAttribute("seriesDataGroupByConsumeCategory", seriesDataGroupByConsumeCategory);
        model.addAttribute("costList", costList);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        return "PersonalFinance/analyse/analyseCostByType";
    }

    @RequestMapping(value = {"incomePie"})
    public String incomePie(String start_date, String end_date, Model model) {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        //List<Map<String, String>> seriesDataList = incomeService.selectIncomeByDateGroupByDetail(start_date, end_date);
        List<Income> incomeList = incomeService.selectIncomeByDate(start_date, end_date);
        //List<String> legendDataList = seriesDataList.stream().map(s -> s.get("name")).collect(Collectors.toList());
        Map<String, String> seriesDataMap = incomeList.stream().collect(Collectors.toMap(s -> s.getDetail(), s -> s.getRec_amount()));
        List<String> legendDataList = new ArrayList<>(seriesDataMap.keySet());
        //List<String> legendDataList=new ArrayList<>(Arrays.asList("aa", "bb", "cc", "dd"));
        List<Map<String, String>> seriesDataList = new ArrayList<>();
        for (Map.Entry<String,String> entity:seriesDataMap.entrySet()){
            Map<String,String> map=new HashMap<>(10);
            String key=entity.getKey();
            String value=entity.getValue();
            map.put("name",key);
            map.put("value",value);
            seriesDataList.add(map);
        }
        model.addAttribute("incomeList", incomeList);
        model.addAttribute("legendDataList", legendDataList);
        model.addAttribute("seriesDataList", seriesDataList);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        return "PersonalFinance/analyse/incomePie";
    }



}
