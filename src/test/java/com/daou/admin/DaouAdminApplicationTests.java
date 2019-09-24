package com.daou.admin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daou.admin.login.LoginDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaouAdminApplicationTests {

	@Autowired
	private LoginDao loginDao;
	
	@Test
	public void contextLoads() {
		
		int a = this.loginDao.selectTest();
		assertEquals(a, 3);
	}

}
