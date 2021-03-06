package com.daou.admin.common.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.daou.admin.manager.log.LogService;
import com.daou.admin.manager.log.vo.LogVO;
import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.login.vo.MemberVO;

import ch.qos.logback.classic.Logger;

/**
 * 로깅용 AOP
 * 
 * @author hsyoon
 *
 */
@Component
@Aspect
public class LoggerAspect {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LogService logService;
	
	@Pointcut("@annotation(com.daou.admin.common.annotation.AuthAction)")
	public void authActionPointcut() {}
	
	/**
	 * AuthAction 어노테이션이 지정된 메소드에 대해서만 AOP 수행
	 */
	@Before("authActionPointcut()")
	public void beforeAuthAction(JoinPoint jp) throws Exception{
		
		// request, response 인스턴스 획득
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
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
		
		// 활동분류(ActionType) 획득
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();		
		AuthAction authAction = method.getAnnotation(AuthAction.class);		
		
		// 로그 객체 생성
		LogVO logData = new LogVO();
		logData.setLogUri(req.getRequestURI());
		logData.setLogClass(className);
		logData.setLogMethod(methodName);
		logData.setLogParam(logParam);
		logData.setLogAction(authAction.action().toString());
		logData.setRegId(member.getUserId());
		logData.setRegIp(req.getRemoteAddr());
		
		// 로그 등록
		this.logService.insertLog(logData);
	}
	
}
