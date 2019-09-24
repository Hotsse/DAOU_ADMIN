package com.daou.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.daou.admin.common.service.CommonService;
import com.daou.admin.login.vo.MemberVO;

import ch.qos.logback.classic.Logger;

@Component
public class CommonInterceptor implements HandlerInterceptor {

	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		if(req.getRequestURL().toString().contains("/login"))return true;
		
		boolean isAuthExist = checkMemberAuth(req, res);
		if(!isAuthExist) return false;
		
		if(!isRequestAjax(req)) {
			this.commonService.getMainMenuList(req);
		}
		
		return true;
	}	
	
	private boolean isRequestAjax(HttpServletRequest req) {
	    String requestedWithHeader = req.getHeader("X-Requested-With");
	    return "XMLHttpRequest".equals(requestedWithHeader);
	}
	
	private boolean checkMemberAuth(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			// 인증 세션 없을 시
			if(member == null) {
				System.out.println("member is null");
				logger.debug("member is null");
				res.sendRedirect("/login/logout");
				return false;
			}
			
			// 로그인시 IP != 현재 IP
			String currentIp = req.getRemoteAddr();
			if(!currentIp.equals(member.getCurrentIp())){
				System.out.println("currentIp != memberIp");
				logger.debug("currentIp != memberIp");
				res.sendRedirect("/login/logout");
				return false;
			}
			
			
			// 로그
			req.setAttribute("member", member);
			logger.debug(member.toString());
			
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
}
