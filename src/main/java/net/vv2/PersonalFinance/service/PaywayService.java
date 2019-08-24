package net.vv2.PersonalFinance.service;

import java.util.List;

import net.vv2.PersonalFinance.domain.TPayWayEntity;

public interface PaywayService {
	
	int insertPayway(TPayWayEntity payway);
	
	int updatePayway(TPayWayEntity payway);
	
	int deletePayway(Long Id);
	
	TPayWayEntity selectPaywayById(Long Id);
	
	List<TPayWayEntity> selectPaywayAll();
	
	
	
	

}
