package net.vv2.PersonalFinance.service;

import java.util.List;
import java.util.Map;

public interface TConsumeTypeCategoryService {
	
	List<Map<String,String>> selectConsumeType();
	
	List<String> selectConsumeCategory();
	

}
