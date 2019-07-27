package net.vv2.PersonalFinance.service;

import net.vv2.PersonalFinance.domain.Income;

import java.util.Date;
import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 23:05
 **/
public interface IncomeService {


    /**
     * 添加数据
     * @param income
     * @return int
     */
    int insertIncome(Income income);

    /**
     * 更新数据
     * @param income
     * @return int
     */
    int updateIncome(Income income);

    /**
     * 删除数据
     * @param id
     * @return int
     */
    int deleteIncome(Integer id);

    /**
     * 返回Healthy所有数据
     * @return list
     */
    public List<Income> selectAll();


    /**
     * 根据ID搜索
     * @param id
     * @return
     */
    Income selectIncomeById(Integer id);

    /**
     * 返回Healthy所有数据
     * @return list
     */
    public List<Income> selectIncomeByDate(String start_time, String end_time);

    public float selectSumIncomeByDate(String start_time, String end_time);

}
