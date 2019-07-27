package net.vv2.PersonalFinance.web.Controller;

import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.PersonalFinance.service.impl.IncomeServiceImpl;
import net.vv2.PersonalFinance.service.impl.PropertyServiceImpl;
import net.vv2.baby.domain.User;
import net.vv2.util.TestPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-09 16:46
 **/
@Controller
@RequestMapping(value = "PersonalFinance/analyse")
public class AnalyseController {
    @Autowired
    private IncomeServiceImpl incomeService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private CostServiceImpl costService;


    @RequestMapping("index")
    public String index(Model model){
        return "PersonalFinance/analyse/Analyse";
    }
    @RequestMapping("analyse")
    public String returnanalyse(Model model){
        return "PersonalFinance/analyse/Analyse";
    }


    @RequestMapping("analyseIncome")
    public ModelAndView analyseIncome(String start_date ,String end_date,
                                 HttpSession session,
                                 ModelAndView mv){
        System.out.println(" 准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Float sum_recamount=incomeService.selectSumIncomeByDate(start_date,end_date); ;//准备需要添加的身高体重数据
        System.out.println(sum_recamount);
        //保存数据，判断或失败，并进行相关的跳转
        if (!(null==sum_recamount||sum_recamount==0)){
            String msg = " 添加成功,共收入"+sum_recamount;
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/#tab1\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = " 添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse#tab1\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }



    @RequestMapping("analyseall")
    public ModelAndView analyseall(String start_date ,String end_date,
                                      HttpSession session,
                                      ModelAndView mv) throws IOException {
        System.out.println("准备记录-----------");
        User user = (User) session.getAttribute("user");//获取当前登陆的家长
        Date nowtime = new Date();//创建当前时间
        Float sum_recamount=incomeService.selectSumIncomeByDate(start_date,end_date);
        Float sum_costamount=costService.selectSumCostByDate(start_date,end_date);
        Float start_propertyamount=propertyService.selectSumpropertyByDate(start_date);
        Float end_propertyamount=propertyService.selectSumpropertyByDate(end_date);
        Float subtract_property=(start_propertyamount+sum_recamount)-end_propertyamount;
        Float diffcost=sum_costamount-subtract_property;
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(start_date);
        stringBuffer.append("至");
        stringBuffer.append(end_date);
        stringBuffer.append("期间总共收入:");
        stringBuffer.append(sum_recamount);
        stringBuffer.append("\n");
        stringBuffer.append("总共花费:");
        stringBuffer.append(sum_costamount);
        stringBuffer.append("\n");
        stringBuffer.append("上月（");
        stringBuffer.append(start_date);
        stringBuffer.append("）各账户余额为:");
        stringBuffer.append(start_propertyamount);
        stringBuffer.append("\n");
        stringBuffer.append("本月（");
        stringBuffer.append(end_date);
        stringBuffer.append("）各账户余额为:");
        stringBuffer.append(end_propertyamount);
        stringBuffer.append("，\n本月花费:");
        stringBuffer.append(subtract_property);
        stringBuffer.append("，\n与Cost花费记录,相差");
        stringBuffer.append(diffcost);
        if ((diffcost>10)||((-1)*diffcost>10)){
            stringBuffer.append("不相符，请分析原因\n");
        }else{
            stringBuffer.append("相符。\n");
        }
        System.out.println(stringBuffer.toString());
        String filename=start_date+"-"+end_date+"-"+"analyseall.pdf";
        TestPdf.createPDFWithString(filename,filename,stringBuffer.toString());
        //保存数据，判断或失败，并进行相关的跳转
        if (!(null==sum_recamount||sum_recamount==0)){
            String msg = "添加成功,共收入"+sum_recamount;
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/analyse\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("success");
            return mv;
        }else {
            String msg = "添加失败";
            String url = "<meta http-equiv=\"refresh\" content=\"2;url=/PersonalFinance/analyse/analyse\">";
            mv.addObject("msg", msg);
            mv.addObject("url", url);
            mv.setViewName("err");
            return mv;
        }
    }



}