package net.vv2.SelfManagement.Controller;

import com.xiaoleilu.hutool.date.DateUtil;

import net.vv2.SelfManagement.domain.Weight;
import net.vv2.SelfManagement.service.WeightService;
import net.vv2.baby.domain.Healthy;
import net.vv2.baby.service.impl.HealthyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:49
 **/
@RestController
@RequestMapping("selfmanagement")
public class WeightRestJson {

    @Autowired
    private WeightService weightService;

    @RequestMapping("weight")
    public Map<String,ArrayList<String>> getHealthyRestJson(){
        Map<String,ArrayList<String>> weightMap = new HashMap<String,ArrayList<String>>();
        List<Weight> weightList = weightService.selectWeightAll();
        
        ArrayList<String> weights = new ArrayList<String>();
        ArrayList<String> times = new ArrayList<String>();

        for (Weight weight : weightList){
            weights.add(weight.getWeight()+"");
            times.add(DateUtil.formatDate(weight.getCreate_time()));
        }
       
        weightMap.put("weight",weights);
        weightMap.put("times",times);
        return weightMap;
    }
}
