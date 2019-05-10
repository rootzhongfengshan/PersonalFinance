package net.vv2.PersonalFinance.service;

import net.vv2.PersonalFinance.domain.Cost;

import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 23:05
 **/
public interface CostService {


    /**
     * 添加数据
     * @param cost
     * @return int
     */
    int insertCost(Cost cost);

    /**
     * 更新数据
     * @param cost
     * @return int
     */
    int updateCost(Cost cost);

    /**
     * 删除数据
     * @param id
     * @return int
     */
    int deleteCost(Integer id);

    /**
     * 返回Healthy所有数据
     * @return list
     */
    public List<Cost> selectAll();


    /**
     * 根据ID搜索
     * @param id
     * @return
     */
    public Cost selectCostById(Integer id);

    public List<Cost> selectCostByDate(String start_time, String end_time);

    public float selectSumCostByDate(String start_time,String end_time);
}
