package net.vv2.personal.finance.service;

import net.vv2.personal.finance.domain.Budget;

import java.util.List;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:05
 **/
public interface BudgetService {


    /**
     * 添加数据
     *
     * @param budget
     * @return int
     */
    int insertBudget(Budget budget);

    /**
     * 更新数据
     *
     * @param budget
     * @return int
     */
    int updateBudget(Budget budget);

    /**
     * 删除数据
     *
     * @param id
     * @return int
     */
    int deleteBudget(Integer id);

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    List<Budget> selectAll();

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    List<Budget> selectBudgetByMonth(String month);


    /**
     * 根据ID搜索
     *
     * @param id
     * @return
     */
    Budget selectBudgetById(Integer id);

    Integer checkIfExistsBudgetRecordByMonth(String month);

}
