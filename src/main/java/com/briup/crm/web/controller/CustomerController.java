package com.briup.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.service.CustomerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/findAllCustomerByPage/{curPage}")
	public String findAllCustomerByPage(@PathVariable int curPage, HttpServletRequest request) {
		PageInfo<CstCustomer> customerinfo = customerService.findAllCustomerByPage(curPage, 5);
		request.setAttribute("customerinfo", customerinfo);
		return "/customer/customer";
	}
	
	@RequestMapping("/findAllCustomer")
	public String findAllCustomer(HttpServletRequest request) {
		List<CstCustomer> customerlist = customerService.findAllCustomer();
		request.setAttribute("customerlist", customerlist);
		return "/customer/customer";
	}
	
	@RequestMapping("/saveOrUpdateCustomer")
	@ResponseBody
	public String saveOrUpdateCustomer(CstCustomer customer) {
		customerService.saveOrUpdateCustomer(customer);
		return "保存成功";
	}
	
	@RequestMapping("/deleteCustomerById/{custId}")
	@ResponseBody
	public String deleteCustomerById(@PathVariable long custId) {
		customerService.deleteCustomerById(custId);
		return "删除成功";
	}
	
	@RequestMapping("/findCustomerById/{custId}")
	@ResponseBody
	public CstCustomer findCustomerById(@PathVariable long custId) {
		CstCustomer customer = customerService.findCustomerById(custId);
		return customer;
	}
	
	@RequestMapping("/findCustomerLike/{curPage}")
	public String findCustomerLike(@PathVariable int curPage, CstCustomer customer, HttpServletRequest request) {
		PageInfo<CstCustomer> customerinfo = customerService.findCustomerLike(curPage, 5, customer);
		request.setAttribute("customerinfo", customerinfo);
		return "customer/customer";
	}
}
