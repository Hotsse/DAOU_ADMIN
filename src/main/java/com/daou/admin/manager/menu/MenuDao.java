package com.daou.admin.manager.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.daou.admin.common.manage.SqlSessionManager;
import com.daou.admin.manager.dept.vo.DeptVO;
import com.daou.admin.manager.menu.vo.MenuAuthVO;
import com.daou.admin.manager.role.vo.RoleVO;

/**
 * 메뉴 관리 DAO
 * 
 * @author hsyoon
 *
 */
@Repository
public class MenuDao extends SqlSessionManager {
	
	// menu
	/**
	 * 계층별 메뉴 리스트 획득
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getAllMenuList(){
		return this.daouSqlSession.selectList("common.menu.getAllMenuList");
	}
	
	/**
	 * 메뉴 정보 획득
	 * 
	 * @param menuIdx
	 * @return
	 */
	public Map<String, Object> getMenu(int menuIdx){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuIdx", menuIdx);
		
		return this.daouSqlSession.selectOne("common.menu.getMenu", param);		
	}
	
	/**
	 * 메뉴 리스트 획득
	 * 
	 * @param menuLv
	 * @param parentMenuIdx
	 * @return
	 */
	public List<Map<String, Object>> getMenuList(int menuLv, int parentMenuIdx) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuLv", menuLv);
		param.put("parentMenuIdx", parentMenuIdx);
		
		return this.daouSqlSession.selectList("common.menu.getMenuList", param);
	}
		
	/**
	 * 메뉴 등록
	 * 
	 * @param param
	 * @return
	 */
	public int insertMenu(Map<String, Object> param) {
		return this.daouSqlSession.insert("common.menu.insertMenu", param);
	}
	
	/**
	 * 메뉴 수정
	 * 
	 * @param param
	 * @return
	 */
	public int updateMenu(Map<String, Object> param) {
		return this.daouSqlSession.update("common.menu.updateMenu", param);
	}
	
	/**
	 * 메뉴 삭제
	 * 
	 * @param param
	 * @return
	 */
	public int deleteMenu(Map<String, Object> param) {
		return this.daouSqlSession.delete("common.menu.deleteMenu", param);
	}	
	
	// menu/auth
	/**
	 * 메뉴 권한 정보 획득
	 * 
	 * @param menuPath
	 * @param userDept
	 * @param userRole
	 * @return
	 */
	public MenuAuthVO getMenuAuth(String menuPath, int userDept, int userRole) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuPath", menuPath);
		param.put("userDept", userDept);
		param.put("userRole", userRole);
		
		return this.daouSqlSession.selectOne("common.menu.getMenuAuth", param);		
	}
	
	/**
	 * 메뉴 권한 리스트 획득
	 * 
	 * @param menuIdx
	 * @return
	 */
	public List<MenuAuthVO> getMenuAuthList(int menuIdx){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuIdx", menuIdx);
		
		return this.daouSqlSession.selectList("common.menu.getMenuAuthList", param);
	}
	
	/**
	 * 메뉴 권한 등록
	 * 
	 * @param param
	 * @return
	 */
	public int insertMenuAuth(Map<String, Object> param) {
		return this.daouSqlSession.insert("common.menu.insertMenuAuth", param);
	}
	
	/**
	 * 메뉴 권한 수정
	 * 
	 * @param param
	 * @return
	 */
	public int updateMenuAuth(Map<String, Object> param) {
		return this.daouSqlSession.update("common.menu.updateMenuAuth", param);
	}
	
	/**
	 * 메뉴 권한 삭제
	 * 
	 * @param param
	 * @return
	 */
	public int deleteMenuAuth(Map<String, Object> param) {
		return this.daouSqlSession.delete("common.menu.deleteMenuAuth", param);
	}
	
	// dept
	/**
	 * 부서 리스트 획득
	 * 
	 * @return
	 */
	public List<DeptVO> getDeptList() {
		return this.daouSqlSession.selectList("common.menu.getDeptList");
	}
	
	// role
	/**
	 * 직책 리스트 획득
	 * 
	 * @return
	 */
	public List<RoleVO> getRoleList() {
		return this.daouSqlSession.selectList("common.menu.getRoleList");
	}

}
