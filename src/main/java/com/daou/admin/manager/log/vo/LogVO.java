package com.daou.admin.manager.log.vo;

import lombok.Data;

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
	
	public int getLogIdx() {
		return logIdx;
	}
	public void setLogIdx(int logIdx) {
		this.logIdx = logIdx;
	}
	public String getLogUri() {
		return logUri;
	}
	public void setLogUri(String logUri) {
		this.logUri = logUri;
	}
	public String getLogClass() {
		return logClass;
	}
	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}
	public String getLogMethod() {
		return logMethod;
	}
	public void setLogMethod(String logMethod) {
		this.logMethod = logMethod;
	}
	public String getLogParam() {
		return logParam;
	}
	public void setLogParam(String logParam) {
		this.logParam = logParam;
	}
	public String getLogAction() {
		return logAction;
	}
	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	
	@Override
	public String toString() {
		return "LogVO [logIdx=" + logIdx + ", logUri=" + logUri + ", logClass=" + logClass + ", logMethod=" + logMethod
				+ ", logParam=" + logParam + ", logAction=" + logAction + ", regId=" + regId + ", regDt=" + regDt
				+ ", regIp=" + regIp + "]";
	}
	
}
