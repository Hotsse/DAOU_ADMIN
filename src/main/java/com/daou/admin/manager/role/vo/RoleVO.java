package com.daou.admin.manager.role.vo;

import lombok.Data;

@Data
public class RoleVO {

	private int roleIdx;
	private String roleNm;
	
	public int getRoleIdx() {
		return roleIdx;
	}
	public void setRoleIdx(int roleIdx) {
		this.roleIdx = roleIdx;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	
	@Override
	public String toString() {
		return "RoleVO [roleIdx=" + roleIdx + ", roleNm=" + roleNm + "]";
	}
}
