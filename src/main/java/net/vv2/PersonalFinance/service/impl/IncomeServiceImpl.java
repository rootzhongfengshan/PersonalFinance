package net.vv2.PersonalFinance.service.impl;

import net.vv2.PersonalFinance.domain.Income;
import net.vv2.PersonalFinance.mapper.IncomeMapper;
import net.vv2.PersonalFinance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
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
    public List<Income> selectIncomeByDate(String  start_time, String end_time) {
        return incomeMapper.selectIncomeByDate(start_time,end_time);
    }
    @Override
    public float selectSumIncomeByDate(String start_time,String end_time){
        return incomeMapper.selectSumIncomeByDate(start_time,end_time);
    }
}
