package com.daou.admin.common.util;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * SHA256 암호화 모듈
 * 
 * @author hsyoon
 *
 */
public class SHA256Util {
	
	public String encrypt(String origin) {
		
		String result = null;
		
		try {
			result = DigestUtils.sha256Hex(origin);
		}
		catch(Exception e) {
			
		}				
		
		return result;
	}

}
