package net.vv2.PersonalFinance.web.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.vv2.PersonalFinance.domain.Cost;
import net.vv2.PersonalFinance.service.CostService;
import net.vv2.PersonalFinance.service.impl.CostServiceImpl;
import net.vv2.util.ParseExcelDataUtil;
import  com.xiaoleilu.hutool.date.DateUtil;

@Controller
@RequestMapping(value = "PersonalFinance/importdata")
public class ImportDataControl {
    @Autowired
    private CostService costService;

	@RequestMapping("recordcost")
    public String index(Model model){
        return "PersonalFinance/record/CostRecordByExcel";
    }
	
	@RequestMapping("uploadexcel")
    public String uploadparse(@RequestParam("file") MultipartFile file,Model model) throws IOException, Exception{
		String [] cellKeys = ParseExcelDataUtil.gettablekeycfg("importcost");
		List<Map<String,Object>> result= ParseExcelDataUtil.parseExcelFile(file.getInputStream(), cellKeys);
		int size=result.size();
		List<Cost> costlist=new ArrayList<Cost>();
		for (Map<String,Object> map : result) {
			Cost cost =new Cost();
			cost.setConsume_date(DateUtil.parseDate((String) map.get("paytime")));
			cost.setConsume_name((String)map.get("shopname"));
			cost.setProject_fee(Float.parseFloat((String) map.get("fee")));
			cost.setConsume_fee(Float.parseFloat((String) map.get("fee")));
			cost.setConsume_type((String)map.get("shopname"));
			cost.setConsume_category((String)map.get("shopname"));
			cost.setPay_way("蚂蚁花呗");
			cost.setRemarks((String)map.get("partner"));
			int a=costService.insertCost(cost);
			System.out.println("------------"+a);
		}
       return "PersonalFinance/record/CostRecordByExcel";
    }

}
