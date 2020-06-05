package net.vv2.personal.finance.service.impl;

import net.vv2.personal.finance.domain.Income;
import net.vv2.personal.finance.mapper.IncomeMapper;
import net.vv2.personal.finance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:07
 **/
@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    @Override
    public int insertIncome(Income income) {
        return incomeMapper.insertIncome(income);
    }

    @Override
    public int updateIncome(Income income) {
        return incomeMapper.updateIncome(income);
    }

    @Override
    public int deleteIncome(Integer id) {
        return incomeMapper.deleteIncome(id);
    }

    @Override
    public List<Income> selectAll() {
        return incomeMapper.selectAll();
    }

    @Override
    public Income selectIncomeById(Integer id) {
        return incomeMapper.selectIncomeById(id);
    }

    @Override
    public List<Income> selectIncomeByDate(String start_date, String end_date) {
        return incomeMapper.selectIncomeByDate(start_date, end_date);
    }

    /**
     * selectIncomeByDateGroupByDetail
     *
     * @param start_date
     * @param end_date
     * @return
     */
    @Override
    public List<Map<String, String>> selectIncomeByDateGroupByDetail(String start_date, String end_date) {
        return incomeMapper.selectIncomeByDateGroupByDetail(start_date, end_date);
    }

    /**
     * 返回Healthy所有数据
     *
     * @param start_date
     * @param end_date
     * @return list
     */
    @Override
    public List<Map<String, Object>> selectIncomeByDateReturnMap(String start_date, String end_date) {
        return incomeMapper.selectIncomeByDateReturnMap(start_date, end_date);
    }

    @Override
    public float selectSumIncomeByDate(String start_date, String end_date) {
        return incomeMapper.selectSumIncomeByDate(start_date, end_date);
    }
}
