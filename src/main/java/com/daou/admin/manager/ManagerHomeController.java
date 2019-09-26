package com.daou.admin.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/manager")
public class ManagerHomeController {
	
	@RequestMapping(value="")
	public String home() throws Exception {
		return "manager/home";
	}
	
}
