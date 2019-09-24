package com.daou.admin.login;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;

@Repository
public class LoginDao extends SqlSessionManager {

	public int selectTest() {
		return this.eventSqlSession.selectOne("common.test.selectTest");
	}	
}
