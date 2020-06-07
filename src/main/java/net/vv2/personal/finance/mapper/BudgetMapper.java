package net.vv2.personal.finance.mapper;

import net.vv2.personal.finance.domain.Budget;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 22:58
 **/
@Mapper
public interface BudgetMapper {

    @Insert("insert into t_budget(id, budgetMonth, budgetName, budgetValue,budgetRemark) values(#{id},#{budgetMonth},#{budgetName},#{budgetValue},#{budgetRemark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBudget(Budget budget);

    int updateBudget(Budget budget);

    int deleteBudget(Integer id);

    @Select("select * from t_budget order by id")
    List<Budget> selectAll();

    Budget selectBudgetById(Integer id);

    @Select("select 1 from t_budget where budgetMonth=str_to_date(#{month}, '%Y-%m-%d') limit 1")
    Integer checkIfExistsBudgetRecordByMonth(@Param("month") String month);

    @Select("select * from t_budget where budgetMonth=str_to_date(#{month}, '%Y-%m-%d') order by id")
    List<Budget> selectBudgetByMonth(@Param("month") String month);
}
