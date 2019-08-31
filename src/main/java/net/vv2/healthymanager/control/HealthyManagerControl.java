package net.vv2.healthymanager.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.vv2.SelfManagement.domain.Weight;
import net.vv2.SelfManagement.service.WeightService;

@Controller
@RequestMapping("HealthyManager")
public class HealthyManagerControl {
	@Autowired
	private WeightService weightService;

	@RequestMapping("index")
	public String gotoIndex(Model model) {

		return "HealthyManager/index";

	}

	@RequestMapping("record")
	public String gotoRecord(Model model) {

		return "HealthyManager/record";

	}

	@RequestMapping("saveWeight")
	public ModelAndView saveWeight(String weight_value, ModelAndView mv) {
		Float weightValue=Float.valueOf(weight_value);
		Weight weight=new Weight(weightValue);
		if (weightService.saveWeight(weight) > 0) {
			String msg = "数据添加成功！";
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

}
