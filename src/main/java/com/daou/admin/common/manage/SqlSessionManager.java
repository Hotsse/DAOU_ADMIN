package com.daou.admin.common.manage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * SqlSession 생성 관리 모듈
 * 
 * @author hsyoon
 *
 */
public class SqlSessionManager {

	@Autowired
	@Qualifier("daouSqlSessionTemplate")
	protected SqlSession daouSqlSession;
}
