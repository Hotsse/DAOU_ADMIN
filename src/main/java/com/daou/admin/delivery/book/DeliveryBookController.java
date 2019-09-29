package com.daou.admin.delivery.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daou.admin.common.annotation.AuthAction;
import com.daou.admin.common.annotation.type.ActionType;

/**
 * 교재배송 관리
 * 
 * @author hsyoon
 *
 */
@Controller
@RequestMapping(value="/delivery/book")
public class DeliveryBookController {
	
	/**
	 * 교재배송 관리 인덱스
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method = {RequestMethod.GET})
	public String index() throws Exception {
		
		return "redirect:/delivery/book/retrieve";
	}
	
	
	/**
	 * 교재배송 조회
	 * 
	 * @return
	 * @throws Exception
	 * @AuthAction RETRIEVE
	 */
	@AuthAction(action=ActionType.RETRIEVE)
	@RequestMapping(value="/retrieve", method = {RequestMethod.GET})
	public String retrieve() throws Exception {
		
		return "delivery/book/retrieve";
	}
	
	
	/**
	 * 교재배송 등록/수정
	 * 
	 * @return
	 * @throws Exception
	 * @AuthAction WRITE
	 */
	@AuthAction(action=ActionType.WRITE)
	@RequestMapping(value="/write", method = {RequestMethod.POST})
	@ResponseBody
	public String write() throws Exception {
		
		return "200 OK";
	}
	
	/**
	 * 교재배송 삭제
	 * 
	 * @return
	 * @throws Exception
	 * @AuthAction DELETE
	 */
	@AuthAction(action=ActionType.DELETE)
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public String delete() throws Exception {
		
		return "200 OK";
	}
	
	
	/**
	 * 교재배송 다운로드
	 * 
	 * @return
	 * @throws Exception
	 * @AuthAction DOWNLOAD
	 */
	@AuthAction(action=ActionType.DOWNLOAD)
	@RequestMapping(value="/download", method = {RequestMethod.POST})
	@ResponseBody
	public String download() throws Exception {
		
		return "200 OK";
	}
	
}
