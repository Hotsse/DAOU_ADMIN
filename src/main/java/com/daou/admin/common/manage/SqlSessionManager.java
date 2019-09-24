package com.daou.admin.common.manage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SqlSessionManager {

	@Autowired
	@Qualifier("eventSqlSessionTemplate")
	protected SqlSession eventSqlSession;
}
