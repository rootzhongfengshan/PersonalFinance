package net.vv2.personal.finance.web.controller;

import java.util.*;

import javax.mail.MessagingException;

import net.vv2.util.HandleDateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lowagie.text.DocumentException;
import com.xiaoleilu.hutool.date.DateUtil;

import net.vv2.personal.finance.domain.Cost;
import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.domain.Property;
import net.vv2.personal.finance.service.MailService;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.IncomeServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import net.vv2.util.FloatUtil;
import net.vv2.util.PdfUtil;
import net.vv2.util.WriteFileUtil;

@Controller
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
		List<String> datelists=propertyService.selectAllOrderByRecordDate();
		model.addAttribute("datelists",datelists);
        return "PersonalFinance/sentemail/SentMailWithCost";
    }

    @RequestMapping("GotoSentMailWithEchars")
    public String gotoEchars(Model model) {
        Double[] valueArray = {1.23, 4.57, 2.38, 4.598, 32.21, 24.3, 3.1};
        List<String> xNameList = new ArrayList<String>(Arrays.asList("qwe", "req", "eeg", "we", "e", "wr", "wf"));
        List<Double> yValueList = new ArrayList<Double>(Arrays.asList(valueArray));
        model.addAttribute("xNameList", xNameList);
        model.addAttribute("yValueList", yValueList);
        return "PersonalFinance/sentemail/testEchars";
    }

	// @Scheduled(cron="0 45 17 * * ?")
	// @RequestMapping("sentmailwithcostmessage")
	public String SentEverydayCostDataByEmail(String start_date, String end_date,Model model) throws MessagingException {
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
		
		List<String> datelists=propertyService.selectAllOrderByRecordDate();
		model.addAttribute("datelists",datelists);
        return "PersonalFinance/sentemail/SentMailWithCost";
	}

	@RequestMapping("sentmailwithcostmessage")
	public String SentReportByEmail(String start_date, String end_date,Model model) throws MessagingException, DocumentException, Exception {
		Date today = DateUtil.date();
		// start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
		// String end_date=DateUtil.formatDate(today);
		float incomesum = incomeService.selectSumIncomeByDate(start_date, end_date);
		List<Income> incomelist = incomeService.selectIncomeByDate(start_date, end_date);
		int incomelen = incomelist.size();
		float costsum = costService.selectSumCostByDate(start_date, end_date);
		List<Cost> costlist = costService.selectCostByDate(start_date, end_date);
		int costlen = costlist.size();
		float startproperty = propertyService.selectSumpropertyByDate(start_date);
		List<Property> endpropertylist=propertyService.selectPropertyByDate(end_date,end_date);
		float endproperty = propertyService.selectSumpropertyByDate(end_date);
		float subtract_property = FloatUtil.sub(FloatUtil.add(startproperty,incomesum),endproperty);
		float diffcost = FloatUtil.sub(costsum , subtract_property);

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
		String filename = title+".pdf";
		String htmlFileName = title+".html";
		PdfUtil.topdf(str, filename);
		WriteFileUtil.WriteStringToHtml(str,htmlFileName);
		mailService.sendAttachmentsMail("zhongfengshan@qq.com", title, title, filename);
		
		mailService.sendAttachmentsMail("zhongfengshan@qq.com", title, title, htmlFileName);

        return "redirect:/PersonalFinance/sentemail/GotoSentMailWithCost";
	}

    @RequestMapping("sentmailwithcostmessage2")
    public String SentReportByEmail2(String start_date, String end_date, Model model) throws MessagingException, DocumentException, Exception {
        Date today = DateUtil.date();
        start_date = HandleDateTools.dealWithStartDate(start_date);
        end_date = HandleDateTools.dealWithEndDate(end_date);
        float incomesum = incomeService.selectSumIncomeByDate(start_date, end_date);
        List<Income> incomelist = incomeService.selectIncomeByDate(start_date, end_date);
        int incomelen = incomelist.size();


        float costsum = costService.selectSumCostByDate(start_date, end_date);
        List<Cost> costlist = costService.selectCostByDate(start_date, end_date);
        int costlen = costlist.size();


        float startproperty = propertyService.selectSumpropertyByDate(start_date);
        List<Property> endpropertylist = propertyService.selectPropertyByDate(end_date, end_date);
        float endproperty = propertyService.selectSumpropertyByDate(end_date);

        float subtract_property = FloatUtil.sub(FloatUtil.add(startproperty, incomesum), endproperty);
        float diffcost = FloatUtil.sub(costsum, subtract_property);

        String title = start_date + "至" + end_date + "个人财务分析报告";
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
        String str = templateEngine.process("detailReport", context);
        mailService.sendHtmlMail("zhongfengshan@qq.com", title, str);
        String filename = title + ".pdf";
        String htmlFileName = title + ".html";
        PdfUtil.topdf(str, filename);
        WriteFileUtil.WriteStringToHtml(str, htmlFileName);
        mailService.sendAttachmentsMail("zhongfengshan@qq.com", title, title, filename);

        mailService.sendAttachmentsMail("zhongfengshan@qq.com", title, title, htmlFileName);

        List<String> datelists = propertyService.selectAllOrderByRecordDate();
        model.addAttribute("datelists", datelists);
        return "PersonalFinance/sentemail/SentMailWithCost";
    }

    @RequestMapping("sentmailwithEmailEchars")
    public String SentReportByEmailEchars(String start_date, String end_date, Model model) throws MessagingException, DocumentException, Exception {
        String title = "LoveEchars";
        Context context = new Context();
        context.setVariable("name", "zhongfs");
        String str = templateEngine.process("testEchars", context);
        mailService.sendHtmlMail("zhongfengshan@qq.com", title, str);
        String filename = title + ".pdf";
        String htmlFileName = title + ".html";
        PdfUtil.topdf(str, filename);
        WriteFileUtil.WriteStringToHtml(str, htmlFileName);
        mailService.sendAttachmentsMail("zhongfengshan@qq.com", title, title, filename);

        mailService.sendAttachmentsMail("zhongfengshan@qq.com", title, title, htmlFileName);


        List<String> datelists = propertyService.selectAllOrderByRecordDate();
        model.addAttribute("datelists", datelists);
        return "PersonalFinance/sentemail/testEchars";

    }
}
