package net.vv2.SelfManagement.service;

import java.util.List;

import net.vv2.SelfManagement.domain.Weight;

/**
 * @author zhongfs
 */
public interface WeightService {

    int saveWeight(Weight weight);

    List<Weight> selectWeightAll();

    List<Weight> selectWeightByStartdateEnddate(String start_date, String end_date);

    Weight selectWeightById(Integer id);


}
