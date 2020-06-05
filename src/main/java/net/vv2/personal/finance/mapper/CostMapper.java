package net.vv2.personal.finance.mapper;

import net.vv2.personal.finance.domain.Cost;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 22:58
 **/
@Mapper
public interface CostMapper {


    /**
     * 插入一条数据
     * @param cost
     * @return int
     */
    @Insert("insert into t_detailed_cost_record(id,consume_date,consume_name,project_fee,consume_fee,consume_type,consume_category,pay_way,remarks) values(#{id},#{consume_date},#{consume_name},#{project_fee},#{consume_fee},#{consume_type},#{consume_category},#{pay_way},#{remarks})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertCost(Cost cost);
    
    
    //void saveCostFromMap(@Param("costMap") Map<String,Object> costMap);
    /**
     * 更新数据
     * @param cost
     * @return int
     */
    @Update("update t_detailed_cost_record set id=#{id},consume_date=#{consume_date},consume_name=#{consume_name},project_fee=#{project_fee},consume_fee=#{consume_fee},consume_type=#{consume_type},consume_category=#{consume_category},pay_way=#{pay_way},remarks=#{remarks} where id = #{id}")
    int updateCost(Cost cost);
    /**
     * 删除
     * @param id
     * @return int
     */
    @Delete("delete from t_detailed_cost_record where id = #{id}")
    int deleteCost(Integer id);
    /**
     * 返回所有 Cost 数据
     * @return list
     */
    @Select("select * from t_detailed_cost_record order by consume_date")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "consume_date",property = "consume_date"),
            @Result(column = "consume_name",property = "consume_name"),
            @Result(column = "project_fee",property = "project_fee"),
            @Result(column = "consume_fee",property = "consume_fee"),
            @Result(column = "consume_type",property = "consume_type"),
            @Result(column = "consume_category",property = "consume_category"),
            @Result(column = "pay_way",property = "pay_way"),
            @Result(column = "remarks",property = "remarks")
    })
    List<Cost> selectAll();

    /**
     * 根据ID 搜索返回
     * @param id
     * @return
     */
    @Select("select * from t_detailed_cost_record where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "consume_date",property = "consume_date"),
            @Result(column = "consume_name",property = "consume_name"),
            @Result(column = "project_fee",property = "project_fee"),
            @Result(column = "consume_fee",property = "consume_fee"),
            @Result(column = "consume_type",property = "consume_type"),
            @Result(column = "consume_category",property = "consume_category"),
            @Result(column = "pay_way",property = "pay_way"),
            @Result(column = "remarks",property = "remarks")
    })
    Cost selectCostById(Integer id);

    /**
     * 根据 start_date搜索返回
     * @param start_date
     * @param end_date
     * @return
     */
    @Select("select * from t_detailed_cost_record where  consume_date between #{start_date} and #{end_date} order by consume_date")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "consume_date",property = "consume_date"),
            @Result(column = "consume_name",property = "consume_name"),
            @Result(column = "project_fee",property = "project_fee"),
            @Result(column = "consume_fee",property = "consume_fee"),
            @Result(column = "consume_type",property = "consume_type"),
            @Result(column = "consume_category",property = "consume_category"),
            @Result(column = "pay_way",property = "pay_way"),
            @Result(column = "remarks",property = "remarks")
    })
    List<Cost> selectCostByDate(@Param("start_date") String start_date, @Param("end_date") String end_date);


    @Select("select IFNULL(sum(consume_fee),0) from t_detailed_cost_record where  consume_date between #{start_date} and #{end_date}")
    float selectSumCostByDate(@Param("start_date") String start_date, @Param("end_date") String end_date);


    @Select("select consume_type,cast(sum(consume_fee) AS CHAR) consume_fee  from t_detailed_cost_record where  consume_date between #{start_date} and #{end_date} group by consume_type")
    List<Map<String, String>> queryCostListByDateGroupByConsumeType(@Param("start_date") String start_date, @Param("end_date") String end_date);

    @Select("select consume_category,cast(sum(consume_fee) AS CHAR) consume_fee  from t_detailed_cost_record where  consume_date between #{start_date} and #{end_date} group by consume_category")
    List<Map<String, String>> queryCostListByDateGroupByConsumeCategory(@Param("start_date") String start_date, @Param("end_date") String end_date);

    @Select("select * from t_detailed_cost_record where  consume_date between #{start_date} and #{end_date} order by consume_date")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "consume_date", property = "consume_date"),
            @Result(column = "consume_name", property = "consume_name"),
            @Result(column = "project_fee", property = "project_fee"),
            @Result(column = "consume_fee", property = "consume_fee"),
            @Result(column = "consume_type", property = "consume_type"),
            @Result(column = "consume_category", property = "consume_category"),
            @Result(column = "pay_way", property = "pay_way"),
            @Result(column = "remarks", property = "remarks")
    })
    List<Map<String, Object>> selectCostByDateReturnMap(@Param("start_date") String start_date, @Param("end_date") String end_date);
}
