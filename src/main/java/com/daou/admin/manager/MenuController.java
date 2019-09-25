package com.daou.admin.manager;

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

@Controller
@RequestMapping(value="/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/index", method = {RequestMethod.GET})
	public String index(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "redirect:/menu/retrieve";
	}
	
	@AuthAction(action=ActionType.ADMIN)
	@RequestMapping(value="/retrieve", method = {RequestMethod.GET})
	public String retrieve(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> menuList = this.menuService.getAllMenuList();
		model.put("menuList", menuList);
		
		return "menu/retrieve";
	}
	
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
		
		return "menu/write";
	}
	
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
		
		return "redirect:/menu/retrieve";
	}
	
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
	
	
}
