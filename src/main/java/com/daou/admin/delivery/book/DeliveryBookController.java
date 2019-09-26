package com.daou.admin.delivery.book;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;

@Controller
@RequestMapping(value="/delivery/book")
public class DeliveryBookController {
	
	@RequestMapping(value="", method = {RequestMethod.GET})
	public String index(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "redirect:/delivery/book/retrieve";
	}
	
	@AuthAction(action=ActionType.RETRIEVE)
	@RequestMapping(value="/retrieve", method = {RequestMethod.GET})
	public String retrieve(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "delivery/book/retrieve";
	}
	
}