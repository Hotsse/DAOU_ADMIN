package com.daou.admin.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 배송 메뉴
 * 
 * @author hsyoon
 *
 */
@Controller
@RequestMapping(value="/delivery")
public class DeliveryHomeController {
	
	@RequestMapping(value="", method = {RequestMethod.GET})
	public String index() throws Exception {
		
		return "delivery/home";
	}
	
}
