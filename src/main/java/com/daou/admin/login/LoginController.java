package com.daou.admin.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daou.admin.login.vo.MemberVO;

/**
 * 로그인 인증 Controller
 * 
 * @author hsyoon
 *
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	/**
	 * 로그인 페이지
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login", method = {RequestMethod.GET})
	public String login(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		// 인증 세션 존재 시 홈 화면으로 리다이렉트
		HttpSession session = req.getSession();
		if(session.getAttribute("member") != null) {
			return "redirect:/home/main";
		}
		
		return "login/login";
	}
	
	
	/**
	 * 로그인 시도
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String tryLogin(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
				
		try {
			String userId = param.get("userId").toString();
			String userPw = param.get("userPw").toString();
			
			MemberVO member = this.loginService.selectDaouMember(userId, userPw); // ID/PW 기반 계정 정보 획득
			
			if(member != null) {
				this.loginService.setMemberSession(member, req); // 인증 세션 생성, 최근 로그인 정보 저장
				return "redirect:/home/main";
			}
		}
		catch(NullPointerException npe) {
			return "redirect:/login/login";
		}
		
		return "redirect:/login/login";
	}
		
	/**
	 * 로그아웃
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout", method = {RequestMethod.GET})
	public String logout(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		this.loginService.destroySession(req); // 인증 세션 삭제
		
		return "redirect:/login/login";
	}
}
