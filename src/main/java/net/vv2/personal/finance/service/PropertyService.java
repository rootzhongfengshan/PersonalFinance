package net.vv2.personal.finance.service;

import net.vv2.personal.finance.domain.Property;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 23:05
 **/
public interface PropertyService {


    /**
     * 添加数据
     * @param property
     * @return int
     */
    int insertProperty(Property property);

    /**
     * 更新数据
     * @param property
     * @return int
     */
    int updateProperty(Property property);

    /**
     * 删除数据
     * @param id
     * @return int
     */
    int deleteProperty(Integer id);

    /**
     * 返回Healthy所有数据
     * @return list
     */
    public List<Property> selectAll();

    
    public List<String> selectAllOrderByRecordDate();
      

    /**
     * 根据ID搜索
     * @param id
     * @return
     */
    Property selectPropertyById(Integer id);

    public List<Property> selectPropertyByDate(String start_date,String end_date);

    float selectSumpropertyByDate(String start_date);

    List<Map<String, Object>> selectPropertyByDateReturnMap(String start_date, String end_date);
}
