package com.daou.admin.manager.menu.vo;

import lombok.Data;

/**
 * 메뉴별 권한 정보 Value Object
 * 
 * @author hsyoon
 *
 */
@Data
public class MenuAuthVO {
	
	private int menuIdx;
	private int deptIdx;
	private String deptNm;
	private int roleIdx;
	private String roleNm;
	private String isRetrieveYn;
	private String isWriteYn;
	private String isDeleteYn;
	private String isDownloadYn;
	
	public int getMenuIdx() {
		return menuIdx;
	}
	public void setMenuIdx(int menuIdx) {
		this.menuIdx = menuIdx;
	}
	public int getDeptIdx() {
		return deptIdx;
	}
	public void setDeptIdx(int deptIdx) {
		this.deptIdx = deptIdx;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
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
	public String getIsRetrieveYn() {
		return isRetrieveYn;
	}
	public void setIsRetrieveYn(String isRetrieveYn) {
		this.isRetrieveYn = isRetrieveYn;
	}
	public String getIsWriteYn() {
		return isWriteYn;
	}
	public void setIsWriteYn(String isWriteYn) {
		this.isWriteYn = isWriteYn;
	}
	public String getIsDeleteYn() {
		return isDeleteYn;
	}
	public void setIsDeleteYn(String isDeleteYn) {
		this.isDeleteYn = isDeleteYn;
	}
	public String getIsDownloadYn() {
		return isDownloadYn;
	}
	public void setIsDownloadYn(String isDownloadYn) {
		this.isDownloadYn = isDownloadYn;
	}
	
	@Override
	public String toString() {
		return "MenuAuthVO [menuIdx=" + menuIdx + ", deptIdx=" + deptIdx + ", deptNm=" + deptNm + ", roleIdx=" + roleIdx
				+ ", roleNm=" + roleNm + ", isRetrieveYn=" + isRetrieveYn + ", isWriteYn=" + isWriteYn + ", isDeleteYn="
				+ isDeleteYn + ", isDownloadYn=" + isDownloadYn + "]";
	}
	
}
