package com.briup.crm.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.CstServiceExample;
import com.briup.crm.dao.CstServiceMapper;
import com.briup.crm.service.ServeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ServeServiceImp implements ServeService {

	@Autowired
	private CstServiceMapper serviceMapper;
	
	@Override
	public PageInfo<CstService> findServiceByUserName(int curpage, int size, String username) {
		PageHelper.startPage(curpage, size);
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrDisposeEqualTo(username);
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public PageInfo<CstService> findServiceLike(int curpage, int size, String svrCustName, String svrType) {
		PageHelper.startPage(curpage, size);
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrCustNameLike("%"+svrCustName+"%")
								.andSvrTypeLike("%"+svrType+"%");
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public void saveOrUpdate(CstService service) {
		if(service.getSvrId()==null)
			serviceMapper.insertSelective(service);
		else
			serviceMapper.updateByPrimaryKeySelective(service);
	}

	@Override
	public CstService findServiceById(long svrId) {
		CstService service = serviceMapper.selectByPrimaryKey(svrId);
		return service;
	}

	@Override
	public PageInfo<CstService> findAllService(int curpage, int size) {
		PageHelper.startPage(curpage, size);
		CstServiceExample example = new CstServiceExample();
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public PageInfo<CstService> findServiceByTypeAndStatus(int curpage, int size, String type, String status) {
		PageHelper.startPage(curpage, size);
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrTypeLike(type)
								.andSvrStatusLike(status);
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}
}
