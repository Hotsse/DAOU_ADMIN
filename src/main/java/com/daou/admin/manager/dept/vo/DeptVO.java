package com.daou.admin.manager.dept.vo;

import lombok.Data;

@Data
public class DeptVO {
	
	private int deptIdx;
	private String deptNm;
	
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
	
	@Override
	public String toString() {
		return "DeptVO [deptIdx=" + deptIdx + ", deptNm=" + deptNm + "]";
	}
}
