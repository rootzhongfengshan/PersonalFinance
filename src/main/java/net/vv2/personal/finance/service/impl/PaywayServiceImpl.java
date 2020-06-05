package net.vv2.personal.finance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.vv2.personal.finance.domain.TPayWayEntity;
import net.vv2.personal.finance.mapper.PaywayMapper;
import net.vv2.personal.finance.service.PaywayService;
@Service
public class PaywayServiceImpl implements PaywayService{
	
	@Autowired
	private PaywayMapper paywayMapper;

	@Override
	public int insertPayway(TPayWayEntity payway) {
		
		
		// TODO Auto-generated method stub
		return paywayMapper.insertPayway(payway);
	}

	@Override
	public int updatePayway(TPayWayEntity payway) {
		// TODO Auto-generated method stub
		return paywayMapper.updatePayway(payway);
	}

	@Override
	public int deletePayway(Long Id) {
		// TODO Auto-generated method stub
		return paywayMapper.deletePayway(Id);
	}

	@Override
	public TPayWayEntity selectPaywayById(Long Id) {
		// TODO Auto-generated method stub
		return paywayMapper.selectPaywayById(Id);
	}

	@Override
	public List<TPayWayEntity> selectPaywayAll() {
		// TODO Auto-generated method stub
		return paywayMapper.selectPaywayAll();
	}

}
