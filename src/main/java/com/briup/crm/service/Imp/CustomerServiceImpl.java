package com.briup.crm.service.Imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import com.briup.crm.dao.CstCustomerMapper;
import com.briup.crm.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CstCustomerMapper customerMapper;

	@Override
	public List<CstCustomer> findAllCustomer() {
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		return customerlist;
	}

	@Override
	public PageInfo<CstCustomer> findAllCustomerByPage(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		PageInfo<CstCustomer> customerinfo = new PageInfo<CstCustomer>(customerlist);
		return customerinfo;
	}

	@Override
	public void saveOrUpdateCustomer(CstCustomer customer) {
		if (customer.getCustId() == null) {
			customerMapper.insertSelective(customer);
		} else {
			customerMapper.updateByPrimaryKey(customer);
		}
	}

	@Override
	public void deleteCustomerById(long custId) {
		customerMapper.deleteByPrimaryKey(custId);
	}

	@Override
	public CstCustomer findCustomerById(long custId) {
		CstCustomer customer = customerMapper.selectByPrimaryKey(custId);
		return customer;
	}

	@Override
	public PageInfo<CstCustomer> findCustomerLike(int curPage, int size, CstCustomer customer) {
		PageHelper.startPage(curPage, size);
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustNameLike("%"+customer.getCustName()+"%").andCustRegionLike("%"+customer.getCustRegion()+"%").andCustLevelLabelLike("%"+customer.getCustLevelLabel()+"%");
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		PageInfo<CstCustomer> customerinfo = new PageInfo<CstCustomer>(customerlist);
		return customerinfo;
	}

	//获得总的消费金额
	public float getTotal() {
		//查询所有顾客，然后获取每个顾客的消费金额
		float total = 0;
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		for(CstCustomer cust : customerlist) {
			total += cust.getCustTurnover();
		}
		return total;
	}
	
	//获得每个区域的消费金额
	public float getRegionTotal(String region) {
		float total = 0;
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustRegionEqualTo(region);
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		for(CstCustomer cust : customerlist) {
			total += cust.getCustTurnover();
		}
		return total;
	}
	
	//获得每个区域的百分比
	@Override
	public float getRegionPercent(String region) {
		float percent = getRegionTotal(region)/getTotal();
		return percent;
	}
	
	/*    -------------   */
	@Override
	public int getCustByLevel(String level) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustLevelLabelEqualTo(level);
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		return customerlist.size();
	}

	@Override
	public int getCustByCreidt(int credit) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustCreditEqualTo(credit);
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		return customerlist.size();
	}

	@Override
	public int getCustBySatisfy(int satisfy) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustSatisfyEqualTo(satisfy);
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		return customerlist.size();
	}

	@Override
	public Set<String> findAllLevel() {
		Set<String> levelSet = new HashSet<String>();
		List<CstCustomer> customerlist = findAllCustomer();
		for(CstCustomer cust:customerlist) {
			String level = cust.getCustLevelLabel();
			levelSet.add(level);
		}
		return levelSet;
	}

	@Override
	public Set<Integer> findAllCreidt() {
		Set<Integer> creditSet = new HashSet<Integer>();
		List<CstCustomer> customerlist = findAllCustomer();
		for(CstCustomer cust:customerlist) {
			Integer credit = cust.getCustCredit();
			creditSet.add(credit);
		}
		return creditSet;
	}

	@Override
	public Set<Integer> findAllSatisfy() {
		Set<Integer> satisfySet = new HashSet<Integer>();
		List<CstCustomer> customerlist = findAllCustomer();
		for(CstCustomer cust:customerlist) {
			Integer satisfy = cust.getCustSatisfy();
			satisfySet.add(satisfy);
		}
		return satisfySet;
	}
	
}
