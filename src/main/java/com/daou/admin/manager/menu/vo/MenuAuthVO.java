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
}
