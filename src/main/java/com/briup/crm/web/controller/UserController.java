package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.crm.bean.SysUser;
import com.briup.crm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/login"})
	public String tologin(String username,String password,HttpSession session) {
		String url = "";
		try {
			SysUser user = userService.login(username, password);
			session.setAttribute("user", user);
			url = "index";
		}catch(Exception e) {
			session.setAttribute("msg", e.getMessage());
			url = "login";
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
}
