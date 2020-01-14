package com.briup.crm.service;


import com.briup.crm.bean.CstService;
import com.github.pagehelper.PageInfo;

public interface ServeService {
	public PageInfo<CstService> findServiceByUserName(int curpage, int size, String userId);
	public PageInfo<CstService> findServiceLike(int curpage, int size, String svrCustName, String svrType);
	public void saveOrUpdate (CstService service);
	public CstService findServiceById(long svrId);
	public PageInfo<CstService> findAllService(int curpage, int size);
	public PageInfo<CstService> findServiceByTypeAndStatus(int curpage, int size, String type, String status);
}
