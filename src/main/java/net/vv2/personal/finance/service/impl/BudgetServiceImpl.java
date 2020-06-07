package net.vv2.personal.finance.service.impl;

import net.vv2.personal.finance.domain.Budget;
import net.vv2.personal.finance.mapper.BudgetMapper;
import net.vv2.personal.finance.service.BudgetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:07
 **/
@Service
public class BudgetServiceImpl implements BudgetService {

    @Resource
    private BudgetMapper budgetMapper;

    @Override
    public int insertBudget(Budget budget) {
        return budgetMapper.insertBudget(budget);
    }

    @Override
    public int updateBudget(Budget budget) {
        return budgetMapper.updateBudget(budget);
    }

    @Override
    public int deleteBudget(Integer id) {
        return budgetMapper.deleteBudget(id);
    }

    @Override
    public List<Budget> selectAll() {
        return budgetMapper.selectAll();
    }

    /**
     * 返回Healthy所有数据
     *
     * @param month
     * @return list
     */
    @Override
    public List<Budget> selectBudgetByMonth(String month) {
        return budgetMapper.selectBudgetByMonth(month);
    }

    @Override
    public Budget selectBudgetById(Integer id) {
        return budgetMapper.selectBudgetById(id);
    }

    @Override
    public Integer checkIfExistsBudgetRecordByMonth(String month) {
        return budgetMapper.checkIfExistsBudgetRecordByMonth(month);
    }

}
