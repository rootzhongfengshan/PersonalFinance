package net.vv2.personal.finance.web.controller;

import com.lowagie.text.DocumentException;
import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.service.MailService;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.IncomeServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import net.vv2.util.HandleDateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

/**
 * @author zhongfs
 */
@Controller
@RequestMapping(value = "PersonalFinance/report")
public class ReportShowController {
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private CostServiceImpl costService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private MailService mailService;


    @RequestMapping(value = {"index", "/"})
    public String getAccessString(Model model) {
        List<String> datelists = propertyService.selectAllOrderByRecordDate();
        model.addAttribute("datelists", datelists);
        return "PersonalFinance/report/index";
    }

    @RequestMapping("allDetailReport")
    public String SentReportByEmail2(String start_date, String end_date, Model model) throws MessagingException, DocumentException, Exception {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        String incomeSum = String.valueOf(incomeService.selectSumIncomeByDate(start_date, end_date));
        List<Income> incomeList = incomeService.selectIncomeByDate(start_date, end_date);
        int incomeLen = incomeList.size();
        model.addAttribute("incomeSum", incomeSum);
        model.addAttribute("incomeList", incomeList);
        model.addAttribute("incomeLen", incomeLen);
        return "PersonalFinance/report/allDetailReport";
    }

    @RequestMapping("analyseCostInThePastSixMonths")
    public String analyseCostInThePastSixMonths(Model model) {
        Date today = DateUtil.date();
        String currentMonth = DateUtil.beginOfMonth(today).toString("yyyy-MM");
        return "PersonalFinance/report/allDetailReport";

    }

}
