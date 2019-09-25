package com.daou.admin.common.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;
import com.daou.admin.common.vo.MenuAuthVO;
import com.daou.admin.log.LogService;
import com.daou.admin.log.vo.LogVO;
import com.daou.admin.login.vo.MemberVO;
import com.daou.admin.manager.MenuService;

import ch.qos.logback.classic.Logger;

@Component
@Aspect
public class LoggerAspect {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LogService logService;
	
	@Pointcut("@annotation(com.daou.admin.common.annotation.AuthAction)")
	public void authActionPointcut() {}
	
	@Before("authActionPointcut()")
	public void beforeAuthAction(JoinPoint jp) throws Exception{
		
		// request, response 인스턴스 획득
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		
		// 세션 획득
		HttpSession session = req.getSession();		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		// className, methodName 획득
		String className = jp.getTarget().getClass().getName().replace("com.daou.admin.", "");
		String methodName = jp.getSignature().getName();
		
		// 요청 파라미터 획득
		Map<String, String[]> param = req.getParameterMap();
		Iterator<String> keys = param.keySet().iterator();
		
		String logParam = "[";
		while( keys.hasNext() ){
			String key = keys.next();
			logParam += key + "=" + Arrays.toString(param.get(key));
			if(keys.hasNext()) logParam += ",";
		}
		logParam += "]";
		
		// 로그 객체 생성
		LogVO logData = new LogVO();
		logData.setLogUri(req.getRequestURI());
		logData.setLogClass(className);
		logData.setLogMethod(methodName);
		logData.setLogParam(logParam);
		logData.setRegId(member.getUserId());
		logData.setRegIp(req.getRemoteAddr());
		
		// 로그 등록
		this.logService.insertLog(logData);
		
		// 권한 어노테이션 획득
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();		
		AuthAction authAction = method.getAnnotation(AuthAction.class);		
		
		try {
			
			// 일반 메뉴 권한 획득
			MenuAuthVO menuAuth = this.menuService.getMenuAuth(req.getRequestURI(), req);
			
			// 어드민인 경우
			if("Y".equals(member.getIsAdminYn())) {
				// 권한 자동 패스
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
			this.logger.warn("userId = " + member.getUserId() + " / " + "accessIp = " + member.getCurrentIp());
			this.logger.warn("uri = " + req.getRequestURI());
			
			FlashMap flashMap = new FlashMap();
			flashMap.put("redirectMsg", "Unauthorized");
			FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(req);
			flashMapManager.saveOutputFlashMap(flashMap, req, res);
			
			res.sendRedirect("/home/main"); // 메인으로 리다이렉팅
		}
		
	}
	
}
