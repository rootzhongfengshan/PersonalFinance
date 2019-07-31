package net.vv2.PersonalFinance.web.Controller;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.PersonalFinance.domain.Cost;
import net.vv2.PersonalFinance.domain.Income;
import net.vv2.PersonalFinance.domain.Property;
import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.PersonalFinance.service.impl.IncomeServiceImpl;
import net.vv2.PersonalFinance.service.impl.PropertyServiceImpl;
import net.vv2.baby.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance/showdata")
public class ShowDataController {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;


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


    @RequestMapping("ShowIncomeList")
    public String ShowIncomeList(String start_date ,String end_date,
                           HttpSession session,Model model){
        if((start_date.equals("")||start_date.isEmpty())||(end_date.equals("")||end_date.isEmpty())) {
            Date today = DateUtil.date();
            start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
            end_date=DateUtil.formatDate(today);
        }

        List<Income> list =incomeService.selectIncomeByDate(start_date,end_date);
        model.addAttribute("list",list);
        return "PersonalFinance/showdata/IncomeShow";
    }

    @RequestMapping("ShowCostList")
    public String ShowCostList(String start_date ,String end_date,
                                 HttpSession session,Model model){
        if((start_date.equals("")||start_date.isEmpty())||(end_date.equals("")||end_date.isEmpty())) {
            Date today = DateUtil.date();
            start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
            end_date=DateUtil.formatDate(today);
        }
        List<Cost> list =costService.selectCostByDate(start_date,end_date);
        model.addAttribute("list",list);
        return "PersonalFinance/showdata/CostShowList";
    }

    @RequestMapping("ShowPropertyList")
    public String ShowPropertyList(String start_date ,String end_date,
                               HttpSession session,Model model){
        if((start_date.equals("")||start_date.isEmpty())||(end_date.equals("")||end_date.isEmpty())) {
            Date today = DateUtil.date();
            start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
            end_date=DateUtil.formatDate(today);
        }
        List<Property> list = propertyService.selectPropertyByDate(start_date,end_date);
        model.addAttribute("list",list);
        return "PersonalFinance/showdata/PropertyShowList";
    }








}
