package net.vv2.personal.finance.service.impl;

import net.vv2.personal.finance.domain.BudgetConfig;
import net.vv2.personal.finance.mapper.BudgetConfigMapper;
import net.vv2.personal.finance.service.BudgetConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:07
 **/
@Service
public class BudgetConfigServiceImpl implements BudgetConfigService {

    @Resource
    private BudgetConfigMapper budgetConfigMapper;

    @Override
    public int insertBudgetConfig(BudgetConfig budgetConfig) {
        return budgetConfigMapper.insertBudgetConfig(budgetConfig);
    }

    @Override
    public int updateBudgetConfig(BudgetConfig budgetConfig) {
        return budgetConfigMapper.updateBudgetConfig(budgetConfig);
    }

    @Override
    public int deleteBudgetConfig(Integer id) {
        return budgetConfigMapper.deleteBudgetConfig(id);
    }

    @Override
    public List<BudgetConfig> selectAll() {
        return budgetConfigMapper.selectAll();
    }

    @Override
    public BudgetConfig selectBudgetConfigById(Integer id) {
        return budgetConfigMapper.selectBudgetConfigById(id);
    }

}
