package net.vv2.personal.finance.service.impl;

import net.vv2.personal.finance.domain.Cost;
import net.vv2.personal.finance.mapper.CostMapper;
import net.vv2.personal.finance.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
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
    public List<Cost> selectCostByDate(String start_date, String end_date) {
        return costMapper.selectCostByDate(start_date, end_date);
    }

    @Override
    public List<Map<String, Object>> selectCostByDateReturnMap(String start_date, String end_date) {
        return costMapper.selectCostByDateReturnMap(start_date, end_date);
    }

    @Override
    public float selectSumCostByDate(String start_date, String end_date) {
        return costMapper.selectSumCostByDate(start_date, end_date);
    }

    @Override
    public List<Map<String, String>> queryCostListByDateGroupByConsumeType(String start_date, String end_date) {
        return costMapper.queryCostListByDateGroupByConsumeType(start_date, end_date);
    }

    @Override
    public List<Map<String, String>> queryCostListByDateGroupByConsumeCategory(String start_date, String end_date) {
        return costMapper.queryCostListByDateGroupByConsumeCategory(start_date, end_date);
    }

    @Override
    public List<Map<String, String>> selectCostValueByBudgetName(List<String> budgetNameList, String start_date, String end_date) {
        return costMapper.selectCostValueByBudgetName(budgetNameList, start_date, end_date);
    }
}
