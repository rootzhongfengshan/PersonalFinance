package net.vv2.PersonalFinance.web.Controller;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.xiaoleilu.hutool.date.DateUtil;

import net.vv2.PersonalFinance.domain.Cost;
import net.vv2.PersonalFinance.domain.Income;
import net.vv2.PersonalFinance.domain.Property;
import net.vv2.PersonalFinance.service.MailService;
import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.PersonalFinance.service.impl.IncomeServiceImpl;
import net.vv2.PersonalFinance.service.impl.PropertyServiceImpl;
import net.vv2.baby.domain.User;
import net.vv2.util.DoubleUtil;
import net.vv2.util.TestPdf;

@Controller
// @RequestMapping(value="/PersonalFinance/sentemail")
@RequestMapping(value = "PersonalFinance/sentemail")
public class SentEmaiController {
	@Autowired
	private IncomeServiceImpl incomeService;
	@Autowired
	private PropertyServiceImpl propertyService;
	@Autowired
	private CostServiceImpl costService;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private MailService mailService;
	
	@RequestMapping("GotoSentMailWithCost")
    public String gotosaveProperty(Model model){
        return "PersonalFinance/sentemail/SentMailWithCost";
    }

	// @Scheduled(cron="0 45 17 * * ?")
	// @RequestMapping("sentmailwithcostmessage")
	public String SentEverydayCostDataByEmail(String start_date, String end_date) throws MessagingException {
		Date today = DateUtil.date();
		// String
		// start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
		// String end_date=DateUtil.formatDate(today);
		List<Cost> list = costService.selectCostByDate(start_date, end_date);
		String title = "每日消费信息" + end_date;
		Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("list", list);
		context.setVariable("consume_date", end_date);
		float costsum = costService.selectSumCostByDate(start_date, end_date);
		context.setVariable("costsum", costsum);
		String str = templateEngine.process("mailTemplate", context);
		mailService.sendHtmlMail("zhongfengshan@qq.com", title, str);
		return "PersonalFinance/sentemail/sentMailWithCost";
	}

	@RequestMapping("sentmailwithcostmessage")
	public String SentReportByEmail(String start_date, String end_date) throws MessagingException {
		Date today = DateUtil.date();
		// start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
		// String end_date=DateUtil.formatDate(today);
		Float incomesum = incomeService.selectSumIncomeByDate(start_date, end_date);
		List<Income> incomelist = incomeService.selectIncomeByDate(start_date, end_date);
		int incomelen = incomelist.size();

		Float costsum = costService.selectSumCostByDate(start_date, end_date);
		List<Cost> costlist = costService.selectCostByDate(start_date, end_date);
		int costlen = costlist.size();

		Float startproperty = propertyService.selectSumpropertyByDate(start_date);
		List<Property> endpropertylist=propertyService.selectPropertyByDate(end_date,end_date);
		Float endproperty = propertyService.selectSumpropertyByDate(end_date);
		Double subtract_property =DoubleUtil.sub(DoubleUtil.add(startproperty,incomesum),endproperty);
		Double diffcost = DoubleUtil.sub(costsum , subtract_property);

		String title = start_date+"至" + end_date+"个人财务分析报告";
		Context context = new Context();
		context.setVariable("title", title);
		// time
		context.setVariable("startdate", start_date);
		context.setVariable("enddate", end_date);
		// income
		context.setVariable("incomelen", incomelen);
		context.setVariable("incomesum", incomesum);
		context.setVariable("incomelist", incomelist);
		// cost
		context.setVariable("costlen", costlen);
		context.setVariable("costsum", costsum);
		context.setVariable("costlist", costlist);
		// property
		context.setVariable("startproperty", startproperty);
		context.setVariable("endproperty", endproperty);
		context.setVariable("endpropertylist", endpropertylist);
		context.setVariable("subtract_property", subtract_property);
		context.setVariable("diffcost", diffcost);
		context.setVariable("consume_date", end_date);
		String str = templateEngine.process("reportPdfTemplate2", context);
		mailService.sendHtmlMail("zhongfengshan@qq.com", title, str);
		return "PersonalFinance/sentemail/sentMailWithCost";
	}
}
