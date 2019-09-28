package com.daou.admin.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.login.vo.MemberVO;

@Repository
public class LoginDao extends SqlSessionManager {	
	
	/**
	 * 계정 정보 획득
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public MemberVO getDaouMember(String userId, String userPw) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("userPw", userPw);
		
		return this.daouSqlSession.selectOne("common.login.getDaouMember", param);
	}
	
	/**
	 * 최근 로그인 IP 획득
	 * 
	 * @param userId
	 * @return
	 */
	public String getLastIp(String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		
		return this.daouSqlSession.selectOne("common.login.getLastIp", param);
	}	
	
	/**
	 * 최근 로그인 정보 갱신
	 * 
	 * @param userId
	 * @param currentIp
	 * @return
	 */
	public int updateCurrentLoginInfo(String userId, String currentIp) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("currentIp", currentIp);
		
		return this.daouSqlSession.update("common.login.updateCurrentLoginInfo", param);		
	}
}
