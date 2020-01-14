package com.briup.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	@RequestMapping({"/","/tolLogin"})
	public String toLogin() {
		return "login";
	}
}
