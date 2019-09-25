package com.daou.admin.common.aop;

import java.lang.reflect.Method;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;
import com.daou.admin.common.service.CommonService;
import com.daou.admin.common.vo.MenuAuthVO;
import com.daou.admin.login.vo.MemberVO;

import ch.qos.logback.classic.Logger;

@Component
@Aspect
public class LoggerAspect {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonService commonService;
	
	@Pointcut("execution(* com.daou.admin..*Controller.*(..))")
	public void controllerPointcut(){}
	
	@Pointcut("@annotation(com.daou.admin.common.annotation.AuthAction)")
	public void authActionPointcut() {}
	
	@Before("authActionPointcut()")
	public void beforeAuthAction(JoinPoint jp) throws Exception{
		
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		
		
		HttpSession session = req.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		// logPath, logMethod
		String className = jp.getTarget().getClass().getName().replace("com.edw.epm.", "");
		String methodName = jp.getSignature().getName();
		
		// logParam
		String logParam = "";
		Object[] args = jp.getArgs();
		if(args != null){
			if(args.length > 0){
				
				for(Object arg : args){
					logParam += arg.toString();
				}
			}
		}
		
		/*
		this.logger.debug("requestURI = " + req.getRequestURI());
		this.logger.debug("userId = " + member.getUserId());
		this.logger.debug("userIp = " + member.getCurrentIp());
		this.logger.debug("logPath = " + className);
		this.logger.debug("logMethod = " + methodName);
		this.logger.debug("logParam = " + logParam);
		*/
		
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();		
		AuthAction authAction = method.getAnnotation(AuthAction.class);		
		
		try {
			MenuAuthVO menuAuth = this.commonService.getMenuAuth(req.getRequestURI(), req);
			if(menuAuth == null) { // 설정된 권한이 없는 경우
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
			else if(authAction.action() == ActionType.DOWNLOAD
					&& !"Y".equals(menuAuth.getIsDownloadYn())) { // 다운로드 권한이 없는 경우
				throw new AuthenticationException();
			}
			
		}
		catch(AuthenticationException ae) {
			this.logger.warn("Unauthorized access was detected.");
			this.logger.warn("userId = " + member.getUserId() + " / " + "accessIp = " + member.getCurrentIp());
			this.logger.warn("uri = " + req.getRequestURI());
			
			FlashMap flashMap = new FlashMap();
			flashMap.put("redirectMsg", "Unauthorized");
			FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(req);
			flashMapManager.saveOutputFlashMap(flashMap, req, res);
			
			res.sendRedirect("/home/main");
		}
		
	}
	
	/*
	@Before("controllerPointcut()")
	public void beforeController(JoinPoint jp){
		
		// session
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = req.getSession();
		
		if(session.getAttribute("member") != null) {
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			// logPath, logMethod
			String className = jp.getTarget().getClass().getName().replace("com.edw.epm.", "");
			String methodName = jp.getSignature().getName();		
			
			// logParam
			String logParam = "";
			Object[] args = jp.getArgs();
			if(args != null){
				if(args.length > 0){
					
					for(Object arg : args){
						logParam += arg.toString();
					}
				}
			}
			
			this.logger.debug("userId = " + member.getUserId());
			this.logger.debug("userIp = " + member.getCurrentIp());
			this.logger.debug("logPath = " + className);
			this.logger.debug("logMethod = " + methodName);
			this.logger.debug("logParam = " + logParam);
		}		
	}
	*/

}
