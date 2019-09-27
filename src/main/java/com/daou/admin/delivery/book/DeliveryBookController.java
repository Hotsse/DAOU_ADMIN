package com.daou.admin.delivery.book;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@AuthAction(action=ActionType.WRITE)
	@RequestMapping(value="/write", method = {RequestMethod.POST})
	@ResponseBody
	public String write(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "200 OK";
	}
	
	@AuthAction(action=ActionType.DELETE)
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public String delete(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "200 OK";
	}
	
	@AuthAction(action=ActionType.DOWNLOAD)
	@RequestMapping(value="/download", method = {RequestMethod.POST})
	@ResponseBody
	public String download(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "200 OK";
	}
	
}
