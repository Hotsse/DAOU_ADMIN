package com.daou.admin.manager.log.vo;

import lombok.Data;

/**
 * 관리자 활동 정보 VO
 * 
 * @author hsyoon
 *
 */
@Data
public class LogVO {

	private int logIdx;
	private String logUri;
	private String logClass;
	private String logMethod;
	private String logParam;
	private String logAction;
	private String regId;
	private String regDt;
	private String regIp;
}
