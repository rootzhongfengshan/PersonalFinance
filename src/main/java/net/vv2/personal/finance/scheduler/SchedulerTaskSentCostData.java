package net.vv2.personal.finance.scheduler;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.personal.finance.domain.Cost;
import net.vv2.personal.finance.service.MailService;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.IncomeServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Component
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

	@Scheduled(cron = "5 30 22 * * ?")
	public void SentEverydayCostDataByEmailOnTime() throws MessagingException {
		Date today = DateUtil.date();
		// String
		// start_date=DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
		String now_date = DateUtil.formatDate(today);
		List<Cost> list = costService.selectCostByDate(now_date, now_date);
		String title = "每日消费信息" + now_date;
		Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("list", list);
		context.setVariable("consume_date", now_date);
		float costsum = costService.selectSumCostByDate(now_date, now_date);
		context.setVariable("costsum", costsum);
		String str = templateEngine.process("mailTemplate", context);
		mailService.sendHtmlMail("zhongfengshan@qq.com", title, str);

	}

}
