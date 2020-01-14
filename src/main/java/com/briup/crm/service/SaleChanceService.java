package com.briup.crm.service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.github.pagehelper.PageInfo;

public interface SaleChanceService {

	public PageInfo<SalChance> findSalChance(int curPage, int size);
	
	public PageInfo<SalChance> findSalChanceLike(int curPage, int size, String custName, String custRegion);
	
	public void saveOrUpdate(SalChance chance);
	
	public SalChance findChanceById(long chanceId);
	
	public void deleteChanceById(long chanceId);
	
	public PageInfo<SalChance> findChanceByUserId(int curPage, int size, String dueTo);
	
	public PageInfo<SalChance> findChanceByUserNameAndRegion(int curPage, int size, String dueTo, String region);
	
	public SalChanceExtend findChanceWithPlanById(long id);
}
