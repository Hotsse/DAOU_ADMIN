package com.daou.admin.manager.log;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;
import com.daou.admin.manager.log.vo.LogVO;

@Controller
@RequestMapping(value="/manager/log")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="", method = {RequestMethod.GET})
	public String index(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "redirect:/manager/log/retrieve";
	}
	
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/retrieve", method = {RequestMethod.GET, RequestMethod.POST})
	public String retrieve(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		List<LogVO> logList = this.logService.getLogList(param);
		model.put("logList", logList);
		
		return "manager/log/retrieve";
	}
	
}
