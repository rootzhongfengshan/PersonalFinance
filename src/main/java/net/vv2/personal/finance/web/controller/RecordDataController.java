package net.vv2.personal.finance.web.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.personal.finance.domain.Cost;
import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.domain.Property;
import net.vv2.personal.finance.domain.TPayWayEntity;
import net.vv2.personal.finance.service.PaywayService;
import net.vv2.personal.finance.service.TConsumeTypeCategoryService;
import net.vv2.personal.finance.service.impl.CostServiceImpl;
import net.vv2.personal.finance.service.impl.IncomeServiceImpl;
import net.vv2.personal.finance.service.impl.PropertyServiceImpl;
import net.vv2.system.domain.User;
import net.vv2.util.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance")
public class RecordDataController {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;
    @Autowired
    private PaywayService paywayService;
    @Autowired
    private TConsumeTypeCategoryService tConsumeTypeCategoryService;

    private Model getPayWayAndConsumeType(Model model) {
        List<TPayWayEntity> payWays = paywayService.selectPaywayAll();
        List<Map<String, String>> consumeTypeCategory = tConsumeTypeCategoryService.selectConsumeType();
        model.addAttribute("consumeTypeCategory", consumeTypeCategory);
        model.addAttribute("payWays", payWays);
        return model;
    }

    @RequestMapping({"/", "index"})
    public String indexDefault(Model model) {
        model = getPayWayAndConsumeType(model);
        return "PersonalFinance/index";
    }

    @RequestMapping("analyse")
    public String analyse(Model model) {
        return "PersonalFinance/analyse";
    }

    @RequestMapping("mail")
    public String mail(Model model) {
        return "PersonalFinance/mail";
    }

    @RequestMapping("record/saveIncome_0_0_0")
    public String gotoSaveIncome(Model model) {
        return "PersonalFinance/record/Income";
    }

    @RequestMapping("record/saveProperty_0_0_0")
    public String gotoSaveProperty(Model model) {
        return "PersonalFinance/record/PropertyIndex0";
    }

    @RequestMapping("record/saveCost_0_0_0")
    public String gotoSaveCost(Model model) {
        model = getPayWayAndConsumeType(model);
        return "PersonalFinance/record/Cost";
    }

    @RequestMapping("record/savetable_0_0_0")
    public String gotoSavePropertytable(Model model) {
        return "PersonalFinance/record/indexadjast";
    }

    @RequestMapping("record/savelisttest_0_0_0")
    public String gotoSaveAddListTest(Model model) {
        return "PersonalFinance/record/add";
    }


    @RequestMapping("record/saveIncome")
    public ModelAndView saveIncome(String months, String detail, String rec_amount, String rec_date, String remarks,
                                   HttpSession session,
                                   ModelAndView mv) {
        User user = (User) session.getAttribute("user");
        Date nowtime = new Date();
        Income income = new Income(months, detail, rec_amount, DateUtil.parse(rec_date), remarks);
        System.out.println(income);
        //保存数据，判断或失败，并进行相关的跳转
        if (incomeService.insertIncome(income) > 0) {
            String msg = "收入数据添加成功！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveIncome_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        } else {
            String msg = "收入数据添加失败！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveIncome_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping("record/saveProperty")
    public ModelAndView saveProperty(String record_date, String wechat1, String wechat2, String alipay1, String alipay2,
                                     String zhaoshang, String jiaotong, String jianshe, String beijing, String maomingyouzheng,
                                     String beijingyouzheng, String gongshang, String all_sum, String remarks,
                                     HttpSession session,
                                     ModelAndView mv) {
        User user = (User) session.getAttribute("user");
        all_sum = BigDecimalUtils.AddMoreParam(wechat1, wechat2, alipay1, alipay2, zhaoshang, jiaotong, jianshe, beijing, maomingyouzheng, beijingyouzheng, gongshang);
        Date nowTime = new Date();
        Property property = new Property(DateUtil.parse(record_date), wechat1, wechat2, alipay1, alipay2, zhaoshang, jiaotong, jianshe, beijing, maomingyouzheng, beijingyouzheng, gongshang, all_sum, remarks);
        //保存数据，判断或失败，并进行相关的跳转
        int insertFlag = propertyService.insertProperty(property);
        if (insertFlag > 0) {
            String msg = "数据添加成功！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveProperty_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        } else {
            String msg = "数据添加失败！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveProperty_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping("record/saveCost")
    public ModelAndView saveCost(String consume_date, String consume_name, String project_fee, String consume_fee, String consume_type, String consume_category, String pay_way, String remarks,
                                 HttpSession session,
                                 ModelAndView mv) {

        User user = (User) session.getAttribute("user");
        Date nowtime = new Date();
        Cost cost = new Cost(DateUtil.parse(consume_date), consume_name, project_fee, consume_fee, consume_type, consume_category, pay_way, remarks);
        System.out.println(cost);
        //保存数据，判断或失败，并进行相关的跳转
        if (costService.insertCost(cost) > 0) {
            String msg = " 添加成功";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveCost_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        } else {
            String msg = " 添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveCost_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }


}
