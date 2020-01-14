package com.briup.crm.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.Contribution;
import com.briup.crm.dao.extend.CustomerExtendMapper;
import com.briup.crm.service.ContributionService;
import com.briup.crm.service.CustomerService;

@Service
public class ContributionServiceImp implements ContributionService {
	
	@Autowired
	private CustomerExtendMapper custExtendMapper;
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public List<Contribution> findContribution() {
		List<String> regionlist = custExtendMapper.selectRegion();
		List<Contribution> conlist = new ArrayList<Contribution>();
		for(String region :regionlist) {
			Contribution  con = new Contribution();
			con.setName(region);
			con.setY(customerService.getRegionPercent(region));
			conlist.add(con);
		}
		return conlist;
	}

	@Override
	public List<Contribution> findContributionByRegion(String region) {
		List<Contribution> conlist = new ArrayList<Contribution>();
		Contribution con = new Contribution();
		con.setName(region);
		con.setY(customerService.getRegionPercent(region));
		conlist.add(con);
		return conlist;
	}
}
