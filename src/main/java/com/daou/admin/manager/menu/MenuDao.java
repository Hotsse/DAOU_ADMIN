package com.daou.admin.manager.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.manager.dept.vo.DeptVO;
import com.daou.admin.manager.menu.vo.MenuAuthVO;
import com.daou.admin.manager.role.vo.RoleVO;

@Repository
public class MenuDao extends SqlSessionManager {
	
	// menu
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
	
	
	
	// menu/auth
	public MenuAuthVO getMenuAuth(String menuPath, int userDept, int userRole) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuPath", menuPath);
		param.put("userDept", userDept);
		param.put("userRole", userRole);
		
		return this.eventSqlSession.selectOne("common.menu.getMenuAuth", param);		
	}
	
	public List<MenuAuthVO> getMenuAuthList(int menuIdx){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuIdx", menuIdx);
		
		return this.eventSqlSession.selectList("common.menu.getMenuAuthList", param);
	}
	
	public int insertMenuAuth(Map<String, Object> param) {
		return this.eventSqlSession.insert("common.menu.insertMenuAuth", param);
	}
	
	public int updateMenuAuth(Map<String, Object> param) {
		return this.eventSqlSession.update("common.menu.updateMenuAuth", param);
	}
	
	public int deleteMenuAuth(Map<String, Object> param) {
		return this.eventSqlSession.delete("common.menu.deleteMenuAuth", param);
	}
	
	// dept
	public List<DeptVO> getDeptList() {
		return this.eventSqlSession.selectList("common.menu.getDeptList");
	}
	
	// role
	public List<RoleVO> getRoleList() {
		return this.eventSqlSession.selectList("common.menu.getRoleList");
	}

}
