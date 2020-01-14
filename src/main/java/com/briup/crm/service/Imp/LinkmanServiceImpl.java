package com.briup.crm.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.bean.CstLinkmanExample;
import com.briup.crm.dao.CstLinkmanMapper;
import com.briup.crm.service.LinkmanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LinkmanServiceImpl implements LinkmanService {

	@Autowired
	private CstLinkmanMapper linkmanMapper;
	
	@Override
	public PageInfo<CstLinkman> findLinkmanByCustId(long custId, int curPage, int size) {
		PageHelper.startPage(curPage, size);
		CstLinkmanExample example = new CstLinkmanExample();
		example.createCriteria().andLkmCustIdEqualTo(custId);
		List<CstLinkman> linkmanlist = linkmanMapper.selectByExample(example);
		PageInfo<CstLinkman> linkmaninfo = new PageInfo<CstLinkman>(linkmanlist);
		return linkmaninfo;
	}

	@Override
	public void deleteLinkmanById(long lkmId) {
		linkmanMapper.deleteByPrimaryKey(lkmId);
	}

	@Override
	public void saveOrUpdate(CstLinkman lkm) {
		if (lkm.getLkmId() == null) {
			linkmanMapper.insertSelective(lkm);
		} else {
			linkmanMapper.updateByPrimaryKey(lkm);
		}
	}

	@Override
	public CstLinkman findLinkmanById(long lkmId) {
		CstLinkman lkm = linkmanMapper.selectByPrimaryKey(lkmId);
		return lkm;
	}

}
