package com.daou.admin.manager.log;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.manager.log.vo.LogVO;

/**
 * 관리자 활동 관리 DAO
 * @author hsyoon
 *
 */
@Repository
public class LogDao extends SqlSessionManager {
	
	/**
	 * 관리자 활동 획득
	 * 
	 * @param param
	 * @return
	 */
	public List<LogVO> getLogList(Map<String, Object> param){
		return this.daouSqlSession.selectList("common.log.getLogList", param);
	}

	/**
	 * 관리자 활동 등록
	 * 
	 * @param log
	 * @return
	 */
	public int insertLog(LogVO log) {		
		return this.daouSqlSession.insert("common.log.insertLog", log);
	}
}
