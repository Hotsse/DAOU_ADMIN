package com.daou.admin.common.vo;

import lombok.Data;

@Data
public class MenuAuthVO {
	
	private String isRetrieveYn;
	private String isWriteYn;
	private String isDownloadYn;
	
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
	public String getIsDownloadYn() {
		return isDownloadYn;
	}
	public void setIsDownloadYn(String isDownloadYn) {
		this.isDownloadYn = isDownloadYn;
	}
	
	@Override
	public String toString() {
		return "MenuAuthVO [isRetrieveYn=" + isRetrieveYn + ", isWriteYn=" + isWriteYn + ", isDownloadYn="
				+ isDownloadYn + "]";
	}
}
