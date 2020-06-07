package net.vv2.personal.finance.service;

import net.vv2.personal.finance.domain.Cost;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:05
 **/
public interface CostService {


    /**
     * 添加数据
     *
     * @param cost
     * @return int
     */
    int insertCost(Cost cost);

    /**
     * 更新数据
     *
     * @param cost
     * @return int
     */
    int updateCost(Cost cost);

    /**
     * 删除数据
     *
     * @param id
     * @return int
     */
    int deleteCost(Integer id);

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    public List<Cost> selectAll();


    /**
     * 根据ID搜索
     *
     * @param id
     * @return
     */
    Cost selectCostById(Integer id);

    List<Cost> selectCostByDate(String start_date, String end_date);

    List<Map<String, Object>> selectCostByDateReturnMap(String start_date, String end_date);

    float selectSumCostByDate(String start_date, String end_date);

    List<Map<String, String>> queryCostListByDateGroupByConsumeType(String start_date, String end_date);

    List<Map<String, String>> queryCostListByDateGroupByConsumeCategory(String start_date, String end_date);

    List<Map<String, String>> selectCostValueByBudgetName(List<String> budgetNameList, String start_date, String end_date);
}
