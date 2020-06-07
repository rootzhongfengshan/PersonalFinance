package net.vv2.personal.finance.mapper;

import net.vv2.personal.finance.domain.BudgetConfig;
import net.vv2.personal.finance.domain.Cost;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 22:58
 **/
@Mapper
public interface BudgetConfigMapper {

    int insertBudgetConfig(BudgetConfig budgetConfig);

    int updateBudgetConfig(BudgetConfig budgetConfig);

    int deleteBudgetConfig(Integer id);

    @Select("select * from t_budget_config where status=1 order by id")
    List<BudgetConfig> selectAll();

    BudgetConfig selectBudgetConfigById(Integer id);
}
