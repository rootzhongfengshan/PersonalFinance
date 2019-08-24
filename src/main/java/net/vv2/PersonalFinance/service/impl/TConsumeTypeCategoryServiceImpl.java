package net.vv2.PersonalFinance.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.vv2.PersonalFinance.mapper.TConsumeTypeCategoryMapper;
import net.vv2.PersonalFinance.service.TConsumeTypeCategoryService;
@Service
public class TConsumeTypeCategoryServiceImpl implements TConsumeTypeCategoryService{
	
	@Autowired
	private TConsumeTypeCategoryMapper tConsumeTypeCategoryMapper;

	@Override
	public List<Map<String,String>> selectConsumeType() {
		// TODO Auto-generated method stub
		return tConsumeTypeCategoryMapper.selectConsumeType();
	}

	@Override
	public List<String> selectConsumeCategory() {
		// TODO Auto-generated method stub
		return tConsumeTypeCategoryMapper.selectConsumeCategory();
	}

}
