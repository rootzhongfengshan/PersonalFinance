package net.vv2.personal.finance.service;

import net.vv2.personal.finance.domain.BudgetConfig;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:05
 **/
public interface BudgetConfigService {


    /**
     * 添加数据
     *
     * @param budgetConfig
     * @return int
     */
    int insertBudgetConfig(BudgetConfig budgetConfig);

    /**
     * 更新数据
     *
     * @param budgetConfig
     * @return int
     */
    int updateBudgetConfig(BudgetConfig budgetConfig);

    /**
     * 删除数据
     *
     * @param id
     * @return int
     */
    int deleteBudgetConfig(Integer id);

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    List<BudgetConfig> selectAll();


    /**
     * 根据ID搜索
     *
     * @param id
     * @return
     */
    BudgetConfig selectBudgetConfigById(Integer id);

}
