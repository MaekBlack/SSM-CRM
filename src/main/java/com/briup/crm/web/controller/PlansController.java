package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.service.PlanService;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/plan")
public class PlansController {
	
	@Autowired
	private SaleChanceService chanceService;
	
	@Autowired
	private PlanService planService;

	@RequestMapping("/findPlansByUserId/{curPage}")
	public String findPlansByUserId(@PathVariable int curPage, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		PageInfo<SalChance> chanceInfo = chanceService.findChanceByUserId(curPage, 5, user.getUsrName());
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/plans";
	}
	
	@RequestMapping("/findChanceByUserNameAndRegion/{curPage}")
	public String findChanceByUserNameAndRegion(@PathVariable int curPage, String region, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		PageInfo<SalChance> chanceInfo = chanceService.findChanceByUserNameAndRegion(curPage, 5, user.getUsrName(), region);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/plans";
	}
	
	@RequestMapping("/toPlanAdd/{chanceId}")
	public String toPlanAdd(@PathVariable long chanceId, HttpSession session) {
		session.setAttribute("chanceId", chanceId);
		SalChance chance = chanceService.findChanceById(chanceId);
		session.setAttribute("chance", chance);
		return "sales/plan_add";
	}
	
	@RequestMapping("/addPlan")
	public String addPlan(SalPlan plan, HttpSession session) {
		long chanceId = (long) session.getAttribute("chanceId");
		planService.savePlan(plan, chanceId);
		return "forward:/plan/findPlansByUserId/1";
	}
	
	@RequestMapping("/toPlanEdit/{chanceId}")
	public String toPlanEdit(@PathVariable long chanceId, HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanById(chanceId);
		session.setAttribute("chance", chanceExtend);
		session.setAttribute("chanceId", chanceId);
		return "sales/plan_edit";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(SalPlan plan, HttpSession session) {
		long chanceId = (long) session.getAttribute("chanceId");
		plan.setPlaChcId(chanceId);
		planService.saveOrUpdate(plan);
		return "forward:/plan/toPlanEdit/"+chanceId;
	}
	
	@RequestMapping("/findPlanByPlaId/{plaId}")
	@ResponseBody
	public SalPlan findPlanByPlaId(@PathVariable long plaId) {
		SalPlan plan = planService.findPlanByPlaId(plaId);
		return plan;
	}
	
	@RequestMapping("/deletePlanById/{plaId}")
	public String deletePlanById(@PathVariable long plaId, HttpSession session) {
		long chanceId = (long) session.getAttribute("chanceId");
		planService.deletePlanById(plaId);
		return "forward:/plan/toPlanEdit/"+chanceId;
	}
	
	@RequestMapping("/toPlanDetail/{chanceId}")
	public String toPlanDetail(@PathVariable long chanceId, HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanById(chanceId);
		session.setAttribute("chance", chanceExtend);
		return "sales/plan_detail";
	}
}
