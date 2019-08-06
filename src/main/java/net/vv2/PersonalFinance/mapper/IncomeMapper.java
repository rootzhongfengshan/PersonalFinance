package net.vv2.PersonalFinance.mapper;

import net.vv2.PersonalFinance.domain.Income;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:58
 **/
@Mapper
public interface IncomeMapper {


    /**
     * 插入一条数据
     * @param income
     * @return int
     */
    @Insert("insert into t_income_statement(id, months, detail, rec_amount, rec_date, remarks) values(#{id},#{months},#{detail},#{rec_amount},#{rec_date},#{remarks})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertIncome(Income income);
    /**
     * 更新数据
     * @param income
     * @return int
     */
    @Update("update t_income_statement set months = #{months},detail = #{detail},rec_amount = #{rec_Amount},rec_date = #{rec_date},remarks = #{remarks} where id = #{id}")
    int updateIncome(Income income);
    /**
     * 删除
     * @param id
     * @return int
     */
    @Delete("delete from t_income_statement where id = #{id}")
    int deleteIncome(Integer id);
    /**
     * 返回所有 Income 数据
     * @return list
     */
    @Select("select * from t_income_statement")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "months",property = "months"),
            @Result(column = "detail",property = "detail"),
            @Result(column = "rec_amount",property = "rec_amount"),
            @Result(column = "rec_date",property = "rec_date"),
            @Result(column = "remarks",property = "remarks")
    })
    List<Income> selectAll();

    /**
     * 根据ID 搜索返回
     * @param id
     * @return
     */
    @Select("select * from t_income_statement where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "months",property = "months"),
            @Result(column = "detail",property = "detail"),
            @Result(column = "rec_amount",property = "rec_amount"),
            @Result(column = "rec_date",property = "rec_date"),
            @Result(column = "remarks",property = "remarks")
    })
    Income selectIncomeById(Integer id);

    /**
     * 根据 start_date搜索返回
     * @param start_date
     * @param end_date
     * @return
     */
    @Select("select * from t_income_statement where  rec_date between #{start_date} and #{end_date}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "months",property = "months"),
            @Result(column = "detail",property = "detail"),
            @Result(column = "rec_amount",property = "rec_amount"),
            @Result(column = "rec_date",property = "rec_date"),
            @Result(column = "remarks",property = "remarks")
    })
    List<Income> selectIncomeByDate(@Param("start_date") String start_date, @Param("end_date") String end_date);

    @Select("select IFNULL(sum(rec_amount),0) from t_income_statement where  rec_date between #{start_date} and #{end_date}")
    float selectSumIncomeByDate(@Param("start_date") String start_date, @Param("end_date") String end_date);

}
