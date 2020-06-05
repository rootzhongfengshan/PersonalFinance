package net.vv2.personal.finance.mapper;

import java.util.List;

import net.vv2.personal.finance.domain.TPayWayEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaywayMapper {

	
	@Insert("insert into t_pay_way(id,pay_way,info,bankid,remarks" + 
			") values(#{id},#{pay_way},#{info},#{bankid},#{remarks})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
	int insertPayway(TPayWayEntity payway);
	
	
	@Update("update t_pay_way set id=#{id},pay_way=#{pay_way},info=#{info},bankid=#{bankid},remarks=#{remarks} where id = #{id}")
    
	int updatePayway(TPayWayEntity payway);
	@Delete("delete from t_pay_way where id = #{id}")
	int deletePayway(Long id);
	
	@Select("select * from t_pay_way where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "pay_way",property = "pay_way"),
            @Result(column = "info",property = "info"),
            @Result(column = "bankid",property = "bankid"),
            @Result(column = "remarks",property = "remarks") 
    })
	TPayWayEntity selectPaywayById(Long id);
	
	@Select("select * from t_pay_way order by id")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "pay_way",property = "payWay"),
            @Result(column = "info",property = "info"),
            @Result(column = "bankid",property = "bankid"),
            @Result(column = "remarks",property = "remarks") 
    })
	List<TPayWayEntity> selectPaywayAll();

	
	

}
