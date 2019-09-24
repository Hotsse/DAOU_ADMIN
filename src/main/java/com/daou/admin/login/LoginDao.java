package com.daou.admin.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.login.vo.MemberVO;

@Repository
public class LoginDao extends SqlSessionManager {
	
	public MemberVO selectDaouMember(String userId, String userPw) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("userPw", userPw);
		
		return this.eventSqlSession.selectOne("common.login.selectDaouMember", param);
	}
	
	public int updateCurrentLoginInfo(String userId, String currentIp) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("currentIp", currentIp);
		
		return this.eventSqlSession.update("common.login.updateCurrentLoginInfo", param);		
	}
}
