package com.daou.admin.login.vo;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userId;
	private String userNm;
	private int userDept;
	private int userRole;
	private String isAdminYn;
	private String currentIp;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public int getUserDept() {
		return userDept;
	}
	public void setUserDept(int userDept) {
		this.userDept = userDept;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public String getIsAdminYn() {
		return isAdminYn;
	}
	public void setIsAdminYn(String isAdminYn) {
		this.isAdminYn = isAdminYn;
	}
	public String getCurrentIp() {
		return currentIp;
	}
	public void setCurrentIp(String currentIp) {
		this.currentIp = currentIp;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", userNm=" + userNm + ", userDept=" + userDept + ", userRole=" + userRole
				+ ", isAdminYn=" + isAdminYn + ", currentIp=" + currentIp + "]";
	}
}
