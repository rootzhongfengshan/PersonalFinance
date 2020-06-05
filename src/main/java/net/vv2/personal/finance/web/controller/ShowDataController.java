package net.vv2.personal.finance.web.controller;

import net.vv2.personal.finance.domain.Cost;
import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.domain.Property;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.IncomeServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import net.vv2.util.HandleDateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance/showData")
public class ShowDataController {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;


    @RequestMapping("index")
    public String index(Model model) {
        return "PersonalFinance/showData/IncomeShow";
    }

    @RequestMapping("showIncome1")
    public String gotoSaveIncome(Model model) {
        return "PersonalFinance/showData/IncomeShow";
    }

    @RequestMapping("showProperty1")
    public String gotoSaveProperty(Model model) {
        return "PersonalFinance/showData/PropertyShow";
    }

    @RequestMapping("showCost1")
    public String gotoSaveCost(Model model) {
        return "PersonalFinance/showData/CostShow";
    }


    @RequestMapping(value = {"ShowIncomeList", "showIncome"})
    public String ShowIncomeList(String start_date, String end_date,
                                 HttpSession session, Model model) {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<Income> list = incomeService.selectIncomeByDate(start_date, end_date);
        model.addAttribute("list", list);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        return "PersonalFinance/showData/IncomeShow";
    }

    @RequestMapping(value = {"ShowCostList", "showCost"})
    public String ShowCostList(String start_date, String end_date,
                               HttpSession session, Model model) {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<Cost> list = costService.selectCostByDate(start_date, end_date);
        model.addAttribute("list", list);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        return "PersonalFinance/showData/CostShow";
    }

    @RequestMapping(value = {"ShowPropertyList", "showProperty"})
    public String ShowPropertyList(String start_date, String end_date,
                                   HttpSession session, Model model) {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<Property> list = propertyService.selectPropertyByDate(start_date, end_date);
        model.addAttribute("list", list);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        return "PersonalFinance/showData/PropertyShow";
    }


}
