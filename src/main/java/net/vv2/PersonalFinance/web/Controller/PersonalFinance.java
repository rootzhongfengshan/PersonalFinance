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

/**
 *
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance")
public class PersonalFinance {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;


    @RequestMapping("index")
    public String index(Model model){
        return "PersonalFinance/index";
    }

    @RequestMapping("analyse")
    public String analyse(Model model){
        return "PersonalFinance/analyse";
    }

    @RequestMapping("mail")
    public String mail(Model model){
        return "PersonalFinance/mail";
    }

    @RequestMapping("record/saveIncome_0_0_0")
    public String gotosaveIncome(Model model){
        return "PersonalFinance/record/Income";
    }

    @RequestMapping("record/saveProperty_0_0_0")
    public String gotosaveProperty(Model model){
        return "PersonalFinance/record/Property";
    }

    @RequestMapping("record/saveCost_0_0_0")
    public String gotosaveCost(Model model){
        return "PersonalFinance/record/Cost";
    }

    @RequestMapping("record/savetable_0_0_0")
    public String gotosavePropertytable(Model model){
        return "PersonalFinance/record/indexadjast";
    }

    @RequestMapping("record/savelisttest_0_0_0")
    public String gotosaveaddlisttest(Model model){return "PersonalFinance/record/add";}


    @RequestMapping("record/saveIncome")
    public ModelAndView saveIncome(String months, String detail, Float rec_amount, String rec_date, String remarks,
                                 HttpSession session,
                                 ModelAndView mv){
        System.out.println(" 准备收款记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Income income = new Income(months, detail,  rec_amount,  DateUtil.parse(rec_date), remarks);//准备需要添加的身高体重数据
        System.out.println(income);
        //保存数据，判断或失败，并进行相关的跳转
        if (incomeService.insertIncome(income)>0){
            String msg = "收入数据添加成功！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveIncome_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "收入数据添加失败！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveIncome_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping("record/saveProperty")
    public ModelAndView saveProperty(String record_date,Float wechat1,Float wechat2,Float alipay1,Float alipay2,
                                     Float zhaoshang,Float jiaotong,Float jianshe,Float beijing,Float maomingyouzheng,
                                     Float beijingyouzheng,Float gongshang,Float all_sum,String remarks,
                                   HttpSession session,
                                   ModelAndView mv){
        System.out.println("准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        all_sum=wechat1+wechat2+alipay1+alipay2+zhaoshang+jiaotong+jianshe+beijing+maomingyouzheng+beijingyouzheng+gongshang;
        Date nowtime = new Date();//创建当前时间
        Property property = new Property(DateUtil.parse(record_date),wechat1,wechat2,alipay1,alipay2,zhaoshang,jiaotong,jianshe,beijing,maomingyouzheng,beijingyouzheng,gongshang,all_sum,remarks);//准备需要添加的身高体重数据
        System.out.println(property);
        //保存数据，判断或失败，并进行相关的跳转
        if (propertyService.insertProperty(property)>0){
            String msg = "数据添加成功！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveProperty_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "数据添加失败！";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveProperty_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }

    @RequestMapping("record/saveCost")
    public ModelAndView saveCost(String consume_date ,String consume_name,Float project_fee,Float consume_fee,String consume_type,String consume_category,String pay_way,String remarks,
                                   HttpSession session,
                                   ModelAndView mv){
        System.out.println("准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Cost cost = new Cost(DateUtil.parse(consume_date),consume_name,project_fee,consume_fee,consume_type,consume_category,pay_way,remarks);//准备需要添加的身高体重数据
        System.out.println(cost);
        //保存数据，判断或失败，并进行相关的跳转
        if (costService.insertCost(cost)>0){
            String msg = " 添加成功";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveCost_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = " 添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/record/saveCost_0_0_0\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }









}
