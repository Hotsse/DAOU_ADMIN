package com.daou.admin.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.admin.log.vo.LogVO;

@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	
	public void insertLog(LogVO log) {
		
		try {
			this.logDao.insertLog(log);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
