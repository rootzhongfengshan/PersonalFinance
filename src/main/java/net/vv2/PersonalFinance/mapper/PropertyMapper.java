package net.vv2.PersonalFinance.mapper;

import net.vv2.PersonalFinance.domain.Property;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:58
 **/
@Mapper
public interface PropertyMapper {


    /**
     * 插入一条数据
     * @param property
     * @return int
     */
    @Insert("insert into t_property_records(id,record_date,wechat1,wechat2,alipay1,alipay2,zhaoshang,jiaotong,jianshe,beijing,maomingyouzheng,beijingyouzheng,gongshang,all_sum,remarks) values(#{id},#{record_date},#{wechat1},#{wechat2},#{alipay1},#{alipay2},#{zhaoshang},#{jiaotong},#{jianshe},#{beijing},#{maomingyouzheng},#{beijingyouzheng},#{gongshang},#{all_sum},#{remarks})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertProperty(Property property);
    /**
     * 更新数据
     * @param property
     * @return int
     */
    @Update("update t_property_records set record_date=#{record_date},wechat1=#{wechat1},wechat2=#{wechat2},alipay1=#{alipay1},alipay2=#{alipay2},zhaoshang=#{zhaoshang},jiaotong=#{jiaotong},jianshe=#{jianshe},beijing=#{beijing},maomingyouzheng=#{maomingyouzheng},beijingyouzheng=#{beijingyouzheng},gongshang=#{gongshang},all_sum=#{all_sum},remarks=#{remarks} where id = #{id}")
    int updateProperty(Property property);
    /**
     * 删除
     * @param id
     * @return int
     */
    @Delete("delete from t_property_records where id = #{id}")
    int deleteProperty(Integer id);
    /**
     * 返回所有 Property 数据
     * @return list
     */
    @Select("select * from t_property_records")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column="id",property="id"),
            @Result(column="record_date",property="record_date"),
            @Result(column="wechat1",property="wechat1"),
            @Result(column="wechat2",property="wechat2"),
            @Result(column="alipay1",property="alipay1"),
            @Result(column="alipay2",property="alipay2"),
            @Result(column="zhaoshang",property="zhaoshang"),
            @Result(column="jiaotong",property="jiaotong"),
            @Result(column="jianshe",property="jianshe"),
            @Result(column="beijing",property="beijing"),
            @Result(column="maomingyouzheng",property="maomingyouzheng"),
            @Result(column="beijingyouzheng",property="beijingyouzheng"),
            @Result(column="gongshang",property="gongshang"),
            @Result(column="all_sum",property="all_sum"),
            @Result(column = "remarks",property = "remarks")
    })
    List<Property> selectAll();

    /**
     * 根据ID 搜索返回
     * @param id
     * @return
     */
    @Select("select * from t_property_records where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column="id",property="id"),
            @Result(column="record_date",property="record_date"),
            @Result(column="wechat1",property="wechat1"),
            @Result(column="wechat2",property="wechat2"),
            @Result(column="alipay1",property="alipay1"),
            @Result(column="alipay2",property="alipay2"),
            @Result(column="zhaoshang",property="zhaoshang"),
            @Result(column="jiaotong",property="jiaotong"),
            @Result(column="jianshe",property="jianshe"),
            @Result(column="beijing",property="beijing"),
            @Result(column="maomingyouzheng",property="maomingyouzheng"),
            @Result(column="beijingyouzheng",property="beijingyouzheng"),
            @Result(column="gongshang",property="gongshang"),
            @Result(column="all_sum",property="all_sum"),
            @Result(column = "remarks",property = "remarks")
    })
    Property selectPropertyById(Integer id);

    @Select("select * from t_property_records where record_date between #{start_date} and #{end_date}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column="id",property="id"),
            @Result(column="record_date",property="record_date"),
            @Result(column="wechat1",property="wechat1"),
            @Result(column="wechat2",property="wechat2"),
            @Result(column="alipay1",property="alipay1"),
            @Result(column="alipay2",property="alipay2"),
            @Result(column="zhaoshang",property="zhaoshang"),
            @Result(column="jiaotong",property="jiaotong"),
            @Result(column="jianshe",property="jianshe"),
            @Result(column="beijing",property="beijing"),
            @Result(column="maomingyouzheng",property="maomingyouzheng"),
            @Result(column="beijingyouzheng",property="beijingyouzheng"),
            @Result(column="gongshang",property="gongshang"),
            @Result(column="all_sum",property="all_sum"),
            @Result(column = "remarks",property = "remarks")
    })
    List<Property> selectPropertyByDate(@Param("start_date") String start_date, @Param("end_date") String end_date);


    @Select("select all_sum from t_property_records where  record_date = #{start_date} ")
    float selectSumpropertyByDate(@Param("start_date") String start_date);

}
