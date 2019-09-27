package com.daou.admin.manager.log;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.admin.manager.log.vo.LogVO;

@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	
	public List<LogVO> getLogList(Map<String, Object> param){
		
		List<LogVO> logList = null;
		
		try {
			logList = this.logDao.getLogList(param);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return logList;
	}
	
	public void insertLog(LogVO log) {
		
		try {
			this.logDao.insertLog(log);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
