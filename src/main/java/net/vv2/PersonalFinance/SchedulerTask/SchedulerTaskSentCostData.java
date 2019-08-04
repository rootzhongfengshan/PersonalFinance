package net.vv2.PersonalFinance.SchedulerTask;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.PersonalFinance.domain.Cost;
import net.vv2.PersonalFinance.domain.Property;
import net.vv2.PersonalFinance.service.MailService;
import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.PersonalFinance.service.impl.IncomeServiceImpl;
import net.vv2.PersonalFinance.service.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Component
@Controller
@RequestMapping(value ="sentmail")
public class SchedulerTaskSentCostData {

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
    //@Scheduled(cron="0 45 17 * * ?")
    @RequestMapping("sentmailwithcostmessage")
    public String  SentEverydayCostDataByEmail(String  start_date,String end_date) throws MessagingException {
        Date today = DateUtil.date();
        //String  start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
        //String end_date=DateUtil.formatDate(today);
        List<Cost> list =costService.selectCostByDate(start_date,end_date);
        String title="每日消费信息"+end_date;
        Context context = new Context();
        context.setVariable("title",  title);
        context.setVariable("list",  list);
        context.setVariable("consume_date", end_date);
        float costsum=costService.selectSumCostByDate(start_date, end_date);
        context.setVariable("costsum",costsum);
        String str=templateEngine.process("mailTemplate", context);
        mailService.sendHtmlMail("zhongfengshan@qq.com",title,str);
        return "PersonalFinance/sentemail/sentMailWithCost";
    }

    @Scheduled(cron="5 52 21 * * ?")
    public void  SentEverydayCostDataByEmailOnTime() throws MessagingException {
        Date today = DateUtil.date();
        String  start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
        String end_date=DateUtil.formatDate(today);
        List<Cost> list =costService.selectCostByDate(start_date,end_date);
        String message=end_date+"每日消费信息";
        Context context = new Context();
        context.setVariable("message",  message);
        context.setVariable("list",  list);
        String str=templateEngine.process("mailTemplate", context);
        mailService.sendHtmlMail("zhongfengshan@qq.com",message,str);

    }

}
