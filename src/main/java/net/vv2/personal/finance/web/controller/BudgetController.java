package net.vv2.personal.finance.web.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.personal.finance.domain.Budget;
import net.vv2.personal.finance.domain.BudgetConfig;
import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.service.BudgetConfigService;
import net.vv2.personal.finance.service.BudgetService;
import net.vv2.personal.finance.service.CostService;
import net.vv2.personal.finance.service.IncomeService;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.util.BigDecimalUtils;
import net.vv2.util.HandleDateTools;
import net.vv2.util.HandleQuotaShowToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhongfs
 */
@Controller
@RequestMapping(value = {"PersonalFinance/budget"})
public class BudgetController {

    @Autowired
    private IncomeService incomeService;
    @Autowired
    private BudgetConfigService budgetConfigService;
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private CostService costService;

    @RequestMapping(value = {"index", "budget", "/"})
    public String budgetIndex() {
        return "PersonalFinance/budget/budgetIndex";
    }


    @RequestMapping(value = {"recordBudget"})
    public String recordBudget(Model model) {
        String record_date = HandleDateTools.dealWithEndDate("");
        Integer total = budgetService.checkIfExistsBudgetRecordByMonth(record_date);
        if (total == null || total == 0) {
            List<BudgetConfig> budgetConfigList = budgetConfigService.selectAll();
            model.addAttribute("budgetConfigList", budgetConfigList);
            return "PersonalFinance/budget/recordBudget";
        } else {
            model.addAttribute("msg", "本月预算信息已录入，以下是本月录入的预算信息");
            return "forward:/PersonalFinance/budget/queryBudget";
        }
    }

    @RequestMapping(value = {"saveBudget"})
    public String saveBudget(Model model, HttpServletRequest request, HttpServletResponse response) {
        String record_date = request.getParameter("record_date");
        Integer total = budgetService.checkIfExistsBudgetRecordByMonth(record_date);
        if (total == null || total == 0) {
            List<BudgetConfig> budgetConfigList = budgetConfigService.selectAll();
            List<String> budgetNameList = budgetConfigList.stream().map(s -> s.getBudgetName()).collect(Collectors.toList());
            for (String budgetName : budgetNameList) {
                String value = request.getParameter(budgetName);
                Budget budget = new Budget(DateUtil.parse(record_date), budgetName, value);
                budgetService.insertBudget(budget);
            }
            model.addAttribute("msg", "信息录入成功！以下是本月录入的预算信息");
        } else {
            model.addAttribute("msg", "本月预算信息已录入，以下是本月录入的预算信息");
        }

        return "forward:/PersonalFinance/budget/queryBudget";
    }


    @RequestMapping(value = {"queryBudget"})
    public String queryBudget(Model model, HttpServletRequest request, HttpServletResponse response) {
        String record_date = request.getParameter("record_date");
        record_date = HandleDateTools.dealWithEndDate(record_date);
        List<Budget> budgetList = budgetService.selectBudgetByMonth(record_date);
        model.addAttribute("budgetList", budgetList);
        return "PersonalFinance/budget/recordBudget";
    }

    @RequestMapping(value = {"showBudget"})
    public String showBudget(Model model, HttpServletRequest request, HttpServletResponse response) {
        String record_date = request.getParameter("record_date");
        record_date = HandleDateTools.dealWithEndDate(record_date);
        List<Budget> budgetList = budgetService.selectBudgetByMonth(record_date);
        model.addAttribute("record_date", record_date);
        model.addAttribute("budgetList", budgetList);
        return "PersonalFinance/budget/budgetShow";
    }

    @RequestMapping(value = {"budgetAnalyse"})
    public String analyseBudget(String start_date, String end_date, Model model) {
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        List<BudgetConfig> budgetConfigList = budgetConfigService.selectAll();
        List<String> budgetNameList = budgetConfigList.stream().map(s -> s.getBudgetName()).collect(Collectors.toList());
        int budgetNameListSize = budgetNameList.size();
        List<Budget> budgetList = budgetService.selectBudgetByMonth(end_date);
        Map<String, String> budgetValueMapByBudgetName = budgetList.stream().collect(Collectors.toMap(s -> s.getBudgetName(), s -> s.getBudgetValue()));
        //cost
        List<Map<String, String>> costValueMapByBudgetNameList = costService.selectCostValueByBudgetName(budgetNameList, start_date, end_date);
        Map<String, String> costValueMapByBudgetName = costValueMapByBudgetNameList.stream().collect(Collectors.toMap(s -> s.get("consume_category"), s -> s.get("consume_fee")));
        List<String> budgetValueList = new ArrayList<>(budgetNameListSize * 4 / 3 + 1);
        List<String> costValueList = new ArrayList<>(budgetNameListSize * 4 / 3 + 1);
        List<String> compareValueList = new ArrayList<>(budgetNameListSize * 4 / 3 + 1);
        for (String budgetName : budgetNameList) {
            String value = HandleQuotaShowToolUtil.HandleQuotaValue(budgetValueMapByBudgetName.get(budgetName));
            String costValue = HandleQuotaShowToolUtil.HandleQuotaValue(costValueMapByBudgetName.get(budgetName));
            String compareValue = BigDecimalUtils.sub(value, costValue);
            costValue = BigDecimalUtils.getNegate(costValue);
            budgetValueList.add(value);
            costValueList.add(costValue);
            compareValueList.add(compareValue);
        }
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        model.addAttribute("budgetNameList", budgetNameList);
        model.addAttribute("compareValueList", compareValueList);
        model.addAttribute("costValueList", costValueList);
        model.addAttribute("budgetValueList", budgetValueList);

        return "PersonalFinance/budget/budgetAnalyse";
    }
}
