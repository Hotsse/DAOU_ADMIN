package com.daou.admin.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인 홈 메뉴
 * 
 * @author hsyoon
 *
 */
@Controller
public class HomeController {
	
	@RequestMapping(value="")
	public String root() throws Exception {
		
		return "redirect:/home/main";
	}
	
	@RequestMapping(value="/home/main")
	public String main() throws Exception {
		
		return "home/main";
	}
}
