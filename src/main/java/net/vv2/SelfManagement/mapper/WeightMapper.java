package net.vv2.SelfManagement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import net.vv2.SelfManagement.domain.Weight;
@Mapper
public interface WeightMapper {

	@Insert("insert into t_weight(weight,create_time) values(#{weight} ," +
            "now())")
    @Options(useGeneratedKeys = true,keyProperty = "id")
	public int saveWeight(Weight weight);

	@Select("select * from t_weight order by create_time asc")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "weight",property = "weight"),
            @Result(column = "create_time",property = "create_time")
    })
	public List<Weight> selectWeightAll();

	
	@Select("select * from t_weight where create_time between #{start_date} and #{end_date}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "weight",property = "weight"),
            @Result(column = "create_time",property = "create_time")
    })
	public List<Weight> selectWeightByStartdateEnddate(String start_date, String end_date) ;

	
	@Select("select * from t_weight where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "weight",property = "weight"),
            @Result(column = "create_time",property = "create_time")
    })
	public Weight selectWeightById(Integer id) ;

}
