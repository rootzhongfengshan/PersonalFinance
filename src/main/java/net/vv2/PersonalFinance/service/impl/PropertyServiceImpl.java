package net.vv2.PersonalFinance.service.impl;

import net.vv2.PersonalFinance.domain.Property;
import net.vv2.PersonalFinance.mapper.PropertyMapper;
import net.vv2.PersonalFinance.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 23:07
 **/
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public int insertProperty(Property property) {
        return propertyMapper.insertProperty(property);
    }

    @Override
    public int updateProperty(Property property) {
        return propertyMapper.updateProperty(property);
    }

    @Override
    public int deleteProperty(Integer id) {
        return propertyMapper.deleteProperty(id);
    }

    @Override
    public List<Property> selectAll() {
        return propertyMapper.selectAll();
    }
    
    @Override
    public List<String> selectAllOrderByRecordDate() {
        return propertyMapper.selectAllOrderByRecordDate();
    }

    @Override
    public Property selectPropertyById(Integer id) {
        return propertyMapper.selectPropertyById(id);
    }

    @Override
    public List<Property> selectPropertyByDate(String start_date,String end_date){
        return propertyMapper.selectPropertyByDate(start_date,end_date);


    }


    @Override
    public float selectSumpropertyByDate(String start_time){
        return propertyMapper.selectSumpropertyByDate(start_time);
    }

}
