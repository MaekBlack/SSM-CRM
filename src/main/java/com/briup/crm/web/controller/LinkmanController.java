package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.service.LinkmanService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/linkman")
public class LinkmanController {

	@Autowired
	private LinkmanService linkmanService;
	
	@RequestMapping("/findLinkmanByCustId/{custId}/{curPage}")
	public String findLinkmanByCustId(@PathVariable long custId, @PathVariable int curPage, HttpSession session) {
		PageInfo<CstLinkman> linkmaninfo = linkmanService.findLinkmanByCustId(custId, curPage, 5);
		session.setAttribute("linkmaninfo", linkmaninfo);
		session.setAttribute("custId", custId);
		return "customer/linkman";
	}
	
	@RequestMapping("/deleteLinkmanById/{lkmId}")
	@ResponseBody
	public String deleteLinkmanById(@PathVariable long lkmId, HttpSession session) {
		linkmanService.deleteLinkmanById(lkmId);
		Long custId = (Long) session.getAttribute("custId");
		return "forward:/linkman/findLinkmanByCustId/"+custId+"/1";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(CstLinkman lkm, HttpSession session) {
		Long custId = (Long) session.getAttribute("custId");
		lkm.setLkmId(custId);
		linkmanService.saveOrUpdate(lkm);
		return "forward:/linkman/findLinkmanByCustId/"+custId+"/1";
	}
	
	@RequestMapping("/findLinkmanById/{lkmId}")
	@ResponseBody
	public CstLinkman findLinkmanById(@PathVariable long lkmId) {
		CstLinkman lkm = linkmanService.findLinkmanById(lkmId);
		return lkm;
	}
}
