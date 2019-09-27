package com.daou.admin.manager.log;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.manager.log.vo.LogVO;

@Repository
public class LogDao extends SqlSessionManager {
	
	public List<LogVO> getLogList(Map<String, Object> param){
		return this.eventSqlSession.selectList("common.log.getLogList", param);
	}

	public int insertLog(LogVO log) {		
		return this.eventSqlSession.insert("common.log.insertLog", log);
	}
}
