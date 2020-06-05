package net.vv2.personal.finance.service;

import net.vv2.personal.finance.domain.Income;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:05
 **/
public interface IncomeService {


    /**
     * 添加数据
     *
     * @param income
     * @return int
     */
    int insertIncome(Income income);

    /**
     * 更新数据
     *
     * @param income
     * @return int
     */
    int updateIncome(Income income);

    /**
     * 删除数据
     *
     * @param id
     * @return int
     */
    int deleteIncome(Integer id);

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    List<Income> selectAll();


    /**
     * 根据ID搜索
     *
     * @param id
     * @return
     */
    Income selectIncomeById(Integer id);

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    List<Income> selectIncomeByDate(String start_date, String end_date);

    /**
     * selectIncomeByDateGroupByDetail
     *
     * @param start_date
     * @param end_date
     * @return
     */
    List<Map<String, String>> selectIncomeByDateGroupByDetail(String start_date, String end_date);

    /**
     * 返回Healthy所有数据
     *
     * @return list
     */
    List<Map<String, Object>> selectIncomeByDateReturnMap(String start_date, String end_date);

    float selectSumIncomeByDate(String start_date, String end_date);

}
