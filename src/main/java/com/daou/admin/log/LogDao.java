package com.daou.admin.log;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.log.vo.LogVO;

@Repository
public class LogDao extends SqlSessionManager {

	public int insertLog(LogVO log) {		
		return this.eventSqlSession.insert("common.log.insertLog", log);
	}
}
