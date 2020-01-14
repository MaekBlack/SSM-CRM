package com.briup.crm.service;

import com.briup.crm.bean.CstActivity;
import com.github.pagehelper.PageInfo;

public interface ActivityService {

	public PageInfo<CstActivity> findActivitiesByCustId(int curPage, int size, long custId);
	
	public void saveOrUpdate(CstActivity activity);
	
	public CstActivity findActivityById(long atvId);
	
	public void deleteActivityById(long atvId);
}
