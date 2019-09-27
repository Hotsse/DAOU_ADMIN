package com.daou.admin;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.daou.admin.manager.log.LogService;
import com.daou.admin.manager.log.vo.LogVO;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaouAdminApplicationTests {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LogService logService;

	@Test
	public void contextLoads() {	
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logAction", "RETRIEVE");
		
		List<LogVO> logList = this.logService.getLogList(param);
		
		for(LogVO log : logList) {
			this.logger.debug(log.toString());
		}
		
		this.logger.debug("401 = " + HttpStatus.UNAUTHORIZED.value());
	}

}
