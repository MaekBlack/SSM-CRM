package com.briup.crm.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalChanceExample;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.extend.SalChanceExtendMapper;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SaleChanceServiceImpl implements SaleChanceService {

	@Autowired
	private SalChanceMapper chanceMapper;
	
	@Autowired
	private SalChanceExtendMapper chanceExtendMapper; 
	
	@Override
	public PageInfo<SalChance> findSalChance(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		List<SalChance> chanceList = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chanceList);
		return chanceInfo;
	}

	@Override
	public PageInfo<SalChance> findSalChanceLike(int curPage, int size, String custName, String custRegion) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcCustNameLike("%"+custName+"%").andChcAddrLike("%"+custRegion+"%");
		List<SalChance> chanceList = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chanceList);
		return chanceInfo;
	}

	@Override
	public void saveOrUpdate(SalChance chance) {
		if (chance.getChcId() == null) {
			chanceMapper.insertSelective(chance);
		} else {
			chanceMapper.updateByPrimaryKeySelective(chance);
		}
	}

	@Override
	public SalChance findChanceById(long chanceId) {
		SalChance chance = chanceMapper.selectByPrimaryKey(chanceId);
		return chance;
	}

	@Override
	public void deleteChanceById(long chanceId) {
		chanceMapper.deleteByPrimaryKey(chanceId);
	}

	@Override
	public PageInfo<SalChance> findChanceByUserId(int curPage, int size, String dueTo) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo);
		List<SalChance> chancelist = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chancelist);
		return chanceInfo;
	}

	@Override
	public PageInfo<SalChance> findChanceByUserNameAndRegion(int curPage, int size, String dueTo, String region) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToLike(dueTo).andChcAddrLike(region);
		List<SalChance> chancelist = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chancelist);
		return chanceInfo;
	}

	@Override
	public SalChanceExtend findChanceWithPlanById(long id) {
		SalChanceExtend salChanceExtend = chanceExtendMapper.selectChanceWithPlanById(id);
		return salChanceExtend;
	}

}
