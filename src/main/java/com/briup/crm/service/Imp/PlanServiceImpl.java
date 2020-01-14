package com.briup.crm.service.Imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.SalPlanMapper;
import com.briup.crm.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private SalPlanMapper planMapper;
	
	@Autowired
	private SalChanceMapper chanceMapper;
	
	@Override
	public void savePlan(SalPlan plan, long chanceId) {
		plan.setPlaChcId(chanceId);
		planMapper.insertSelective(plan);
		SalChance chance = chanceMapper.selectByPrimaryKey(chanceId);
		chance.setChcStatus(2);
		chanceMapper.updateByPrimaryKey(chance);
	}

	@Override
	public void saveOrUpdate(SalPlan plan) {
		if (plan.getPlaId() == null) {
			planMapper.insertSelective(plan);
		} else {
			planMapper.updateByPrimaryKeySelective(plan);
		}
	}
	
	@Override
	public SalPlan findPlanByPlaId(long plaId) {
		SalPlan plan = planMapper.selectByPrimaryKey(plaId);
		return plan;
	}

	@Override
	public void deletePlanById(long plaId) {
		planMapper.deleteByPrimaryKey(plaId);
	}

}
