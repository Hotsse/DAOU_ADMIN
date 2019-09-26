package com.daou.admin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaouAdminApplicationTests {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {		
	}

}
