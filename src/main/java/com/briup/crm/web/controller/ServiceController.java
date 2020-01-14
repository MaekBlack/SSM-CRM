package com.briup.crm.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ServeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/service")
public class ServiceController {
	
	@Autowired
	private ServeService serveService;
	
	@RequestMapping("/findServiceByUserName/{curpage}")
	public String findServiceByUserName(@PathVariable int curpage, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		String userName = user.getUsrName();
		PageInfo<CstService> serviceInfo = serveService.findServiceByUserName(curpage, 5, userName);
		session.setAttribute("serviceInfo", serviceInfo);
		return "service/service";
	}
	
	@RequestMapping("/findServiceLike/{curPage}")
	public String findServiceLike(@PathVariable int curPage, String svrCustName, String svrType, HttpSession session) {
		PageInfo<CstService> serviceInfo = serveService.findServiceLike(curPage, 5, svrCustName, svrType);
		session.setAttribute("serviceInfo", serviceInfo);
		return "service/service";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstService service) {
		serveService.saveOrUpdate(service);
		return "保存成功";
	}
	
	@RequestMapping("/findServiceById/{svrId}")
	@ResponseBody
	public CstService findServiceById(@PathVariable long svrId) {
		CstService service = serveService.findServiceById(svrId);
		return service;
	}
	
	@RequestMapping("/toServiceDetail/{svrId}")
	public String toServiceDetail(@PathVariable long svrId, HttpSession session) {
		CstService service = serveService.findServiceById(svrId);
		session.setAttribute("service", service);
		return "service/serviceDetail";
	}
	
	@RequestMapping("/findAllService/{curPage}")
	public String findAllService(@PathVariable int curPage, HttpSession session) {
		PageInfo<CstService> serviceInfo = serveService.findAllService(curPage, 5);
		session.setAttribute("serviceInfo", serviceInfo);
		return "service/feedback";
	}
	
	@RequestMapping("/findServiceByTypeAndStatus/{curPage}")
	public String findServiceByTypeAndStatus(@PathVariable int curPage, String type,String status, HttpSession session) {
		PageInfo<CstService> serviceInfo = serveService.findServiceByTypeAndStatus(curPage, 5, type, status);
		session.setAttribute("serviceInfo", serviceInfo);
		return "service/feedback";
	}
}
