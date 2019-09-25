package com.daou.admin.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.common.vo.MenuAuthVO;

@Repository
public class MenuDao extends SqlSessionManager {
	
	public List<Map<String, Object>> getAllMenuList(){
		return this.eventSqlSession.selectList("common.menu.getAllMenuList");
	}	
	
	public List<Map<String, Object>> getMenuList(int menuLv, int parentMenuIdx) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuLv", menuLv);
		param.put("parentMenuIdx", parentMenuIdx);
		
		return this.eventSqlSession.selectList("common.menu.getMenuList", param);
	}
	
	public Map<String, Object> getMenu(int menuIdx){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuIdx", menuIdx);
		
		return this.eventSqlSession.selectOne("common.menu.getMenu", param);		
	}
	
	public int insertMenu(Map<String, Object> param) {
		return this.eventSqlSession.insert("common.menu.insertMenu", param);
	}
	
	public int updateMenu(Map<String, Object> param) {
		return this.eventSqlSession.update("common.menu.updateMenu", param);
	}
	
	public int deleteMenu(Map<String, Object> param) {
		return this.eventSqlSession.delete("common.menu.deleteMenu", param);
	}
	
	
	
	
	public MenuAuthVO getMenuAuth(String menuPath, int userDept, int userRole) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuPath", menuPath);
		param.put("userDept", userDept);
		param.put("userRole", userRole);
		
		return this.eventSqlSession.selectOne("common.menu.getMenuAuth", param);		
	}

}
