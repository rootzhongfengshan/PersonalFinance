package net.vv2.PersonalFinance.SchedulerTask;

import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.PersonalFinance.service.impl.IncomeServiceImpl;
import net.vv2.PersonalFinance.service.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTaskSentCostData {

    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;
    @Scheduled(cron="* 10 * * * ?")
    public void SentCostData(){

        System.out.println("SentCostData");

    }

}
