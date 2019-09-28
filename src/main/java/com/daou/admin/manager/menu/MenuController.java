package com.daou.admin.manager.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;
import com.daou.admin.login.vo.MemberVO;
import com.daou.admin.manager.dept.vo.DeptVO;
import com.daou.admin.manager.menu.vo.MenuAuthVO;
import com.daou.admin.manager.role.vo.RoleVO;

/**
 * 메뉴 관리 Controller
 * 
 * @author hsyoon
 *
 */
@Controller
@RequestMapping(value="/manager/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 메뉴 관리 인덱스
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method = {RequestMethod.GET})
	public String index(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "redirect:/manager/menu/retrieve";
	}
	
	/**
	 * 메뉴 조회
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/retrieve", method = {RequestMethod.GET})
	public String retrieve(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> menuList = this.menuService.getAllMenuList();
		model.put("menuList", menuList);
		
		return "manager/menu/retrieve";
	}
	
	/**
	 * 메뉴 등록 페이지
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/write", method = {RequestMethod.GET})
	public String wrtie(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> menuList = this.menuService.getMenuList(1, 0);
		model.put("menuList", menuList);
		
		// 수정 폼일시, 기존 정보 획득
		try {
			int menuIdx = Integer.parseInt(param.get("menuIdx").toString());
			Map<String, Object> menuInfo = this.menuService.getMenu(menuIdx);
			model.put("menuInfo", menuInfo);
		}
		catch(Exception e) {}
		
		return "manager/menu/write";
	}
	
	
	/**
	 * 메뉴 등록/수정
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @param rttr
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/write", method = {RequestMethod.POST})
	public String write_proc(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model
			, RedirectAttributes rttr) throws Exception {
		
		MemberVO member = (MemberVO)req.getSession().getAttribute("member");
		param.put("userId", member.getUserId());
		
		// 수정
		if(param.get("menuIdx") != null) {			
			boolean result = this.menuService.updateMenu(param);
			if(result) {
				rttr.addFlashAttribute("redirectMsg", "UPDATE-OK");
			}
			else {
				rttr.addFlashAttribute("redirectMsg", "UPDATE-ERROR");
			}
		}
		// 등록
		else {			
			boolean result = this.menuService.insertMenu(param);
			if(result) {
				rttr.addFlashAttribute("redirectMsg", "INSERT-OK");
			}
			else {
				rttr.addFlashAttribute("redirectMsg", "INSERT-ERROR");
			}
		}
		
		return "redirect:/manager/menu/retrieve";
	}
	
	/**
	 * 메뉴 삭제
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		MemberVO member = (MemberVO)req.getSession().getAttribute("member");
		param.put("userId", member.getUserId());		
		
		boolean result = this.menuService.deleteMenu(param);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(result) {
			resultMap.put("result", "OK");
		}
		else {
			resultMap.put("result", "ERROR");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 권한 관리 팝업
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/popupAuth", method = {RequestMethod.GET})
	public String popupAuth(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		List<DeptVO> deptList = this.menuService.getDeptList();
		List<RoleVO> roleList = this.menuService.getRoleList();
		
		model.put("deptList", deptList);
		model.put("roleList", roleList);
		
		try {
			int menuIdx = Integer.parseInt(param.get("menuIdx").toString());
			List<MenuAuthVO> menuAuthList = this.menuService.getMenuAuthList(menuIdx);
			model.put("menuAuthList", menuAuthList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "manager/menu/popupAuth";
	}
	
	/**
	 * 메뉴 권한 등록
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/insertAuth", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertAuth(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		MemberVO member = (MemberVO)req.getSession().getAttribute("member");
		param.put("userId", member.getUserId());
		
		// 유효성 체크
		if(param.get("isRetrieveYn") == null) param.put("isRetrieveYn", "N");
		if(param.get("isWriteYn") == null) param.put("isWriteYn", "N");
		if(param.get("isDeleteYn") == null) param.put("isDeleteYn", "N");
		if(param.get("isDownloadYn") == null) param.put("isDownloadYn", "N");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean result = this.menuService.insertMenuAuth(param);
		if(result) {
			resultMap.put("result", "OK");
		}
		else {
			resultMap.put("result", "ERROR");
		}
		
		return resultMap;
	}
	
	/**
	 * 메뉴 권한 수정
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 * @AuthAction ADMIN
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/updateAuth", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateAuth(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		MemberVO member = (MemberVO)req.getSession().getAttribute("member");
		param.put("userId", member.getUserId());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean result = this.menuService.updateMenuAuth(param);
		if(result) {
			resultMap.put("result", "OK");
		}
		else {
			resultMap.put("result", "ERROR");
		}
		
		return resultMap;
	}
	
	/**
	 * 메뉴 권한 삭제
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/deleteAuth", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> deleteAuth(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		MemberVO member = (MemberVO)req.getSession().getAttribute("member");
		param.put("userId", member.getUserId());		
		
		boolean result = this.menuService.deleteMenuAuth(param);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(result) {
			resultMap.put("result", "OK");
		}
		else {
			resultMap.put("result", "ERROR");
		}
		
		return resultMap;
	}
	
}
