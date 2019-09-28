package com.daou.admin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.daou.admin.common.annotation.type.ActionType;

/**
 * 권한 지정 어노테이션
 * 
 * @author hsyoon
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthAction {
	
	public ActionType action() default ActionType.RETRIEVE; // 기본 권한 = 조회
	
}
