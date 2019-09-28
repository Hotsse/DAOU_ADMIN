package com.daou.admin.user.userInfo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;

/**
 * 회원정보 관리
 * 
 * @author hsyoon
 *
 */
@Controller
@RequestMapping(value="/user/info")
public class UserInfoController {

	/**
	 * 회원정보 관리 인덱스
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
		
		return "redirect:/user/info/retrieve";
	}
	
	/**
	 * 화원정보 조회
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthAction(action=ActionType.RETRIEVE)
	@RequestMapping(value="/retrieve", method = {RequestMethod.GET})
	public String retrieve(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "user/info/retrieve";
	}
	
	/**
	 * 회원정보 등록/수정
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthAction(action=ActionType.WRITE)
	@RequestMapping(value="/write", method = {RequestMethod.POST})
	@ResponseBody
	public String write(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "200 OK";
	}
	
	/**
	 * 회원정보 삭제
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthAction(action=ActionType.DELETE)
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public String delete(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "200 OK";
	}
	
	/**
	 * 회원정보 다운로드
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthAction(action=ActionType.DOWNLOAD)
	@RequestMapping(value="/download", method = {RequestMethod.POST})
	@ResponseBody
	public String download(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		
		return "200 OK";
	}
}
