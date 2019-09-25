package com.daou.admin.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.common.vo.MenuAuthVO;

@Repository
public class CommonDao extends SqlSessionManager {

	public List<Map<String, Object>> getMainMenuList(int menuLv, int parentMenuIdx) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuLv", menuLv);
		param.put("parentMenuIdx", parentMenuIdx);
		
		return this.eventSqlSession.selectList("common.menu.getMainMenuList", param);
	}
	
	public MenuAuthVO getMenuAuth(String menuPath, int userDept, int userRole) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuPath", menuPath);
		param.put("userDept", userDept);
		param.put("userRole", userRole);
		
		return this.eventSqlSession.selectOne("common.menu.getMenuAuth", param);		
	}
}
