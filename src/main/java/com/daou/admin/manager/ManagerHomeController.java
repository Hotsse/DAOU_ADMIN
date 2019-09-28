package com.daou.admin.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;

@Controller
@RequestMapping(value="/manager")
public class ManagerHomeController {
	
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="")
	public String home() throws Exception {
		return "manager/home";
	}
	
}
