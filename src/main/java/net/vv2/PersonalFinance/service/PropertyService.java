package net.vv2.PersonalFinance.service;

import net.vv2.PersonalFinance.domain.Property;

import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
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


    /**
     * 根据ID搜索
     * @param id
     * @return
     */
    Property selectPropertyById(Integer id);

    public List<Property> selectPropertyByDate(String start_date,String end_date);

    float selectSumpropertyByDate(String start_date);
}
