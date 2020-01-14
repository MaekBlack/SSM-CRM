package com.briup.crm.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/chance")
public class SaleChanceController {
	
	@Autowired
	private SaleChanceService chanceService;
	
	@RequestMapping("/findSaleChance/{curPage}")
	public String findSaleChance(@PathVariable int curPage, HttpSession session) {
		PageInfo<SalChance> chanceInfo = chanceService.findSalChance(curPage, 5);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/sales";
	}
	
	@RequestMapping("/findSaleChanceLike/{curPage}")
	public String findSaleChanceLike(@PathVariable int curPage, String custName, String custRegion, HttpSession session) {
		PageInfo<SalChance> chanceInfo = chanceService.findSalChanceLike(curPage, 5, custName, custRegion);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/sales";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(SalChance chance) {
		chanceService.saveOrUpdate(chance);
		return "保存成功";
	}
	
	@RequestMapping("/findChanceById/{chanceId}")
	@ResponseBody
	public SalChance findChanceById(@PathVariable long chanceId) {
		SalChance chance = chanceService.findChanceById(chanceId);
		return chance;
	}
	
	@RequestMapping("/deleteChanceById/{chanceId}")
	@ResponseBody
	public String deleteChanceById(@PathVariable long chanceId) {
		chanceService.deleteChanceById(chanceId);
		return "删除成功";
	}
}
