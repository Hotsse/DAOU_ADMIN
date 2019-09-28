package com.daou.admin.login.vo;

import lombok.Data;

/**
 * 관리자 정보 Value Object
 * 
 * @author hsyoon
 *
 */
@Data
public class MemberVO {
	
	private String userId;
	private String userNm;
	private int userDept;
	private int userRole;
	private String isAdminYn;
	private String currentIp;
}
