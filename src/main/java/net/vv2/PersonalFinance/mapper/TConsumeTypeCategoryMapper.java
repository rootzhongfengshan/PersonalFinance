package net.vv2.PersonalFinance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TConsumeTypeCategoryMapper {
	
	@Select("select consume_type,consume_category from t_consume_type_category order by id")
	List<Map<String,String>> selectConsumeType();
	@Select("select * from t_consume_type_category order by id")
	List<String> selectConsumeCategory();
	
	

}
