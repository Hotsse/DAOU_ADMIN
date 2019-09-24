package com.daou.admin.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daou.admin.login.vo.MemberVO;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login", method = {RequestMethod.GET})
	public String login(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "login/login";
	}
	
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String tryLogin(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		String userId = "", userPw = "";
		try {
			userId = param.get("userId").toString();
			userPw = param.get("userPw").toString();
			
			MemberVO member = this.loginService.selectDaouMember(userId, userPw);
			
			if(member != null) {
								
				this.loginService.setMemberSession(member, req);
				
				return "redirect:/home/main";
			}
		}
		catch(NullPointerException npe) {
			return "redirect:/login/login";
		}
		
		return "redirect:/login/login";
	}
	
	@RequestMapping(value="/logout", method = {RequestMethod.GET})
	public String logout(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		this.loginService.destroySession(req);
		
		return "redirect:/login/login";
	}
}
