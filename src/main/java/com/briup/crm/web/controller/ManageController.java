package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ManageService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@Autowired
	private ManageService manageService;
	
	@RequestMapping("/toRole/{curPage}")
	public String toRole(@PathVariable int curPage, HttpSession session) {
		PageInfo<SysRole> roleInfo = manageService.findAllRole(curPage, 5);
		session.setAttribute("roleInfo", roleInfo);
		return "manage/role";
	}
	
	@RequestMapping("/deleteRoleById/{roleId}")
	@ResponseBody
	public String deleteRoleById(@PathVariable long roleId) {
		manageService.deleteRoleById(roleId);
		return "删除成功";
	}
	
	@RequestMapping("/saveOrUpdateRole")
	@ResponseBody
	public String saveOrUpdateRole(SysRole role) {
		manageService.saveOrUpdateRole(role);
		return "保存成功";
	}

	@RequestMapping("/findRoleById/{roleId}")
	@ResponseBody
	public SysRole findRoleById(@PathVariable long roleId) {
		SysRole role = manageService.findRoleById(roleId);
		return role;
	}
	
	/*    -------------------------------------    */
	
	@RequestMapping("/toUser/{curPage}")
	public String toUser(@PathVariable int curPage, HttpSession session) {
		PageInfo<SysUser> userInfo = manageService.findAllUser(curPage, 5);
		session.setAttribute("userInfo", userInfo);
		return "manage/user";
	}
	
	@RequestMapping("/deleteUserById/{usrId}")
	@ResponseBody
	public String deleteUserById(@PathVariable long usrId) {
		manageService.deleteUserById(usrId);
		return "删除成功";
	}
	
	@RequestMapping("/saveOrUpdateUser")
	@ResponseBody
	public String saveOrUpdateUser(SysUser user) {
		manageService.saveOrUpdateUser(user);
		return "保存成功";
	}
	
	@RequestMapping("/findUserById/{usrId}")
	@ResponseBody
	public SysUser findUserById(@PathVariable long usrId) {
		SysUser user = manageService.findUserById(usrId);
		return user;
	}
	
	@RequestMapping("/findUserByRoleId/{roleId}/{curPage}")
	public String findUserByRoleId(@PathVariable int curPage,@PathVariable long roleId, HttpSession session) {
		PageInfo<SysUser> userInfo = manageService.findUserByRoleId(curPage, 5, roleId);
		session.setAttribute("userInfo", userInfo);
		return "manage/user";
	}
}
