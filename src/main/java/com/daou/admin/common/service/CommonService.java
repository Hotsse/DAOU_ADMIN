package com.daou.admin.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.admin.common.dao.CommonDao;

@Service
public class CommonService {

	@Autowired
	private CommonDao commonDao;
	
}
