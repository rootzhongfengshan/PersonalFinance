package net.vv2.personal.finance.service.impl;

import net.vv2.personal.finance.domain.Property;
import net.vv2.personal.finance.mapper.PropertyMapper;
import net.vv2.personal.finance.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs zhongfengshan@yahoo.com
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
    public float selectSumpropertyByDate(String start_date) {
        return propertyMapper.selectSumpropertyByDate(start_date);
    }

    @Override
    public List<Map<String, Object>> selectPropertyByDateReturnMap(String start_date, String end_date) {
        return propertyMapper.selectPropertyByDateReturnMap(start_date, end_date);
    }

}
