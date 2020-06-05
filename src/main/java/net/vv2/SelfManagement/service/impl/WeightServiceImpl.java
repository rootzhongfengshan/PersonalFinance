package net.vv2.SelfManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.vv2.SelfManagement.domain.Weight;
import net.vv2.SelfManagement.mapper.WeightMapper;
import net.vv2.SelfManagement.service.WeightService;

/**
 * @author zhongfs
 */
@Service
public class WeightServiceImpl implements WeightService {
    @Autowired
    private WeightMapper weightMapper;

    @Override
    public int saveWeight(Weight weight) {
        // TODO Auto-generated method stub
        return weightMapper.saveWeight(weight);
    }

    @Override
    public List<Weight> selectWeightAll() {
        // TODO Auto-generated method stub
        return weightMapper.selectWeightAll();
    }

    @Override
    public List<Weight> selectWeightByStartdateEnddate(String start_date, String end_date) {
        // TODO Auto-generated method stub
        return weightMapper.selectWeightByStartdateEnddate(start_date, end_date);
    }

    @Override
    public Weight selectWeightById(Integer id) {
        // TODO Auto-generated method stub
        return weightMapper.selectWeightById(id);
    }


}
