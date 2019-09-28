package com.daou.admin.common.interceptor;

import java.lang.reflect.Method;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;
import com.daou.admin.common.annotation.type.AuthType;
import com.daou.admin.login.LoginService;
import com.daou.admin.login.vo.MemberVO;
import com.daou.admin.manager.menu.MenuService;
import com.daou.admin.manager.menu.vo.MenuAuthVO;

import ch.qos.logback.classic.Logger;

/**
 * 공통 Interceptor 정의
 * @fileName	CommonInterceptor.java
 * @author	hsyoon
 */
/**
 * @author hsyoon
 *
 */
@Component
public class CommonInterceptor implements HandlerInterceptor {

	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
				
		// 로그인 페이지는 인증 처리 없음
		if(req.getRequestURL().toString().contains("/login"))return true;
		
		// 계정 인증 정보 확인 
		boolean isMemberAuth = checkMemberAuth(req, res);
		if(!isMemberAuth) return false;
		
		// 접근 메뉴 권한 확인
		boolean isMenuAuth = checkMenuAuth(req, res, handler);
		if(!isMenuAuth) return false;
	    
	    // 메뉴 리스트 획득
	    if(!isRequestAjax(req)) {
			this.menuService.getMenuList(req);
		}
	    
		return true;
	}
	
	
	/**
	 * 계정 인증 정보 확인
	 * 
	 * @param req
	 * @param res
	 * @return 인증 성공 여부
	 */
	private boolean checkMemberAuth(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			// 인증 세션 없을 시
			if(member == null) {
				res.sendRedirect("/login/logout");
				return false;
			}
			
			String accessIp = req.getRemoteAddr();
			
			// 세션 IP != 현재 IP			
			if(!accessIp.equals(member.getCurrentIp())){
				this.logger.warn("Forged session access was detected.");
				this.logger.warn("userId = " + member.getUserId() + " / " + "accessIp = " + accessIp);
				this.logger.warn("uri = " + req.getRequestURI());
				restrictUnauthorized(req, res, AuthType.ILLEGAL);
				
				return false;
			}
			
			// DB IP != 현재 IP (중복 로그인 방지)
			String lastIp = this.loginService.selectLastIp(member.getUserId());
			if(lastIp == null || !accessIp.equals(lastIp)) {
				this.logger.warn("Duplicate login detected.");
				this.logger.warn("userId = " + member.getUserId() + " / " + "accessIp = " + accessIp);
				this.logger.warn("uri = " + req.getRequestURI());
				restrictUnauthorized(req, res, AuthType.ILLEGAL);
				
				return false;
			}
			
			// 로그
			req.setAttribute("member", member);		
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 메뉴 접근권한 정보 확인
	 * 
	 * @param req
	 * @param res
	 * @param handler
	 * @return 메뉴 접근 가능여부
	 * @throws Exception
	 */
	private boolean checkMenuAuth(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// 권한 체크
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		HandlerMethod hm = (HandlerMethod)handler; 
	    Method method = hm.getMethod();
	    AuthAction authAction = method.getAnnotation(AuthAction.class);
	    
	    if(authAction != null) {
	    	try {
				// 일반 메뉴 권한 획득
				MenuAuthVO menuAuth = this.menuService.getMenuAuth(req.getRequestURI(), req);
				
				// 어드민인 경우
				if("Y".equals(member.getIsAdminYn())) {
					// 권한 체크 자동 통과
				}
				// 일반 관리자인 경우
				else {
					if(menuAuth == null) { // 설정된 권한이 없는 경우
						throw new AuthenticationException();
					}
					else if(authAction.action() == ActionType.ADMIN) { // 일반 관리자가 어드민 메뉴에 접근한 경우
						throw new AuthenticationException();
					}
					else if(authAction.action() == ActionType.RETRIEVE
							&& !"Y".equals(menuAuth.getIsRetrieveYn())) { // 조회 권한이 없는 경우
						throw new AuthenticationException();
					}
					else if(authAction.action() == ActionType.WRITE
							&& !"Y".equals(menuAuth.getIsWriteYn())) {	// 등록/수정 권한이 없는 경우
						throw new AuthenticationException();
					}
					else if(authAction.action() == ActionType.DELETE
							&& !"Y".equals(menuAuth.getIsDeleteYn())) {	// 삭제 권한이 없는 경우
						throw new AuthenticationException();
					}
					else if(authAction.action() == ActionType.DOWNLOAD
							&& !"Y".equals(menuAuth.getIsDownloadYn())) { // 다운로드 권한이 없는 경우
						throw new AuthenticationException();
					}
				}
				
			}
			catch(AuthenticationException ae) {
				// 해당 권한이 없는 경우 예외 처리
				this.logger.warn("Unauthorized access was detected.");
				this.logger.warn("userId = " + member.getUserId() + " / " + "accessIp = " + req.getRemoteAddr());
				this.logger.warn("uri = " + req.getRequestURI());
				
				restrictUnauthorized(req, res, AuthType.UNAUTHORIZED);				
				return false;
			}
	    }
	    
	    return true;
	}	
	
	/**
	 * 미권한자에 대한 처리
	 * 
	 * @param req
	 * @param res
	 * @param authType
	 */
	private void restrictUnauthorized(HttpServletRequest req, HttpServletResponse res, AuthType authType) {
 
		try {
			if(isRequestAjax(req)) {
				res.sendError(HttpStatus.UNAUTHORIZED.value());
			}
			else {
				if(authType == AuthType.UNAUTHORIZED) {
					FlashMap flashMap = new FlashMap();
					flashMap.put("redirectMsg", "Unauthorized Access");
					FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(req);
					flashMapManager.saveOutputFlashMap(flashMap, req, res);
					
					res.sendRedirect("/home/main"); // 메인으로 리다이렉팅
				}
				else if(authType == AuthType.ILLEGAL) {
					FlashMap flashMap = new FlashMap();
					flashMap.put("redirectMsg", "Illegal Access");
					FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(req);
					flashMapManager.saveOutputFlashMap(flashMap, req, res);
					
					res.sendRedirect("/login/logout");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * Request AJAX 여부 확인
	 * 
	 * @param req
	 * @return
	 */
	private boolean isRequestAjax(HttpServletRequest req) {
	    String requestedWithHeader = req.getHeader("X-Requested-With");
	    return "XMLHttpRequest".equals(requestedWithHeader);
	}
	
}
