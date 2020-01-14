package com.briup.crm.service;

import java.util.List;
import java.util.Set;

import com.briup.crm.bean.CstCustomer;
import com.github.pagehelper.PageInfo;

public interface CustomerService {
	public List<CstCustomer> findAllCustomer();
	
	public PageInfo<CstCustomer> findAllCustomerByPage(int curPage, int size);
	
	public void saveOrUpdateCustomer(CstCustomer customer);
	
	public void deleteCustomerById(long custId);
	
	public CstCustomer findCustomerById(long custId);
	
	public PageInfo<CstCustomer> findCustomerLike(int curPage, int size, CstCustomer customer);
	
	public float getRegionPercent(String region);
	
	//查询所有等级
	public Set<String> findAllLevel();
	
	//查询所有信誉度
	public Set<Integer> findAllCreidt();
	
	//查询所有满意度
	public Set<Integer> findAllSatisfy();
	
	//把不同等级的客户人数查询
	public int getCustByLevel(String level);
	
	//把不同信誉度的客户人数查询
	public int getCustByCreidt(int credit);
	
	//把不同满意度的客户人数查询
	public int getCustBySatisfy(int satisfy);
}
