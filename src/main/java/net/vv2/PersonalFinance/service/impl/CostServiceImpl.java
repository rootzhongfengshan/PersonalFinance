package net.vv2.PersonalFinance.service.impl;

import net.vv2.PersonalFinance.domain.Cost;
import net.vv2.PersonalFinance.mapper.CostMapper;
import net.vv2.PersonalFinance.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 23:07
 **/
@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostMapper costMapper;

    @Override
    public int insertCost(Cost cost) {
        return costMapper.insertCost(cost);
    }

    @Override
    public int updateCost(Cost cost) {
        return costMapper.updateCost(cost);
    }

    @Override
    public int deleteCost(Integer id) {
        return costMapper.deleteCost(id);
    }

    @Override
    public List<Cost> selectAll() {
        return costMapper.selectAll();
    }

    @Override
    public Cost selectCostById(Integer id) {
        return costMapper.selectCostById(id);
    }

    @Override
    public List<Cost> selectCostByDate(String start_time, String end_time){
        return costMapper.selectCostByDate(start_time, end_time);
    }

    @Override
    public float selectSumCostByDate(String start_time,String end_time){
        return costMapper.selectSumCostByDate(start_time,end_time);
    }
}
