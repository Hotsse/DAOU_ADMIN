package com.daou.admin;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daou.admin.common.dao.CommonDao;
import com.daou.admin.common.util.AES256Util;
import com.daou.admin.common.util.SHA256Util;
import com.daou.admin.login.LoginDao;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaouAdminApplicationTests {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private CommonDao commonDao;
	
	@Test
	public void contextLoads() {
		
		SHA256Util sha256 = new SHA256Util();
		String result = sha256.encrypt("ghtp8787@");
		
		List<Map<String, Object>> list = this.commonDao.getAllMenuList();
		for(Map<String, Object> item : list) {
			this.logger.debug(item.get("menu_idx") + "," + item.get("menu_nm"));
		}
		
		System.out.println("len : " + result.length());
		System.out.println(result);
		
	}

}
