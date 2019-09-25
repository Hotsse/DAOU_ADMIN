package com.daou.admin.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.admin.common.dao.CommonDao;
import com.daou.admin.common.vo.MenuAuthVO;
import com.daou.admin.login.vo.MemberVO;

@Service
public class CommonService {

	@Autowired
	private CommonDao commonDao;
	
	public void getMainMenuList(HttpServletRequest req) {

		// 메뉴 정보변수 선언
		List<Map<String, Object>> level1MenuList = null;
		List<Map<String, Object>> level2MenuList = null;
		int currentMenuIdx = 0;

		String currentPath = "";
		String currentLevel1Path = "";
		String currentLevel2Path = "";
		String currentLevel3Path = "";

		// 상위메뉴 리스트 조회
		level1MenuList = commonDao.getMainMenuList(1, 0);

		try {
			// 브라우저 요청 경로 획득
			currentPath = req.getServletPath();
			currentLevel1Path = parseUrlPathByLevel(currentPath, 1);
			currentLevel2Path = parseUrlPathByLevel(currentPath, 2);
			currentLevel3Path = parseUrlPathByLevel(currentPath, 3);

			// 현재 활성화 된 상위메뉴 검색
			for(Map<String, Object> menu : level1MenuList) {
				String dbLevel1Path = parseUrlPathByLevel(menu.get("menuPath").toString(), 1);

				// 상위메뉴 활성화 정보 등록
				if(currentLevel1Path.equals(dbLevel1Path)) {
					int idx = level1MenuList.indexOf(menu);
					menu.put("isActive", "Y");
					level1MenuList.set(idx, menu);

					currentMenuIdx = (int)menu.get("menuIdx");
					break;
				}
			}

			// 현재 활성화된 상위메뉴의 하위메뉴 조회
			if(currentMenuIdx > 0) {
				level2MenuList = commonDao.getMainMenuList(2, currentMenuIdx);

				// 현재 활성화 된 하위메뉴 검색
				for(Map<String, Object> menu : level2MenuList) {
					String dbLevel2Path = parseUrlPathByLevel(menu.get("menuPath").toString(), 2);

					// 하위메뉴 활성화 정보 등록
					if(currentLevel2Path.equals(dbLevel2Path)) {
						int idx = level2MenuList.indexOf(menu);
						menu.put("isActive", "Y");
						level2MenuList.set(idx, menu);

						break;
					}
				}

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("level1MenuList", level1MenuList);
		req.setAttribute("level2MenuList", level2MenuList);

		req.setAttribute("level1MenuPath", currentLevel1Path);
		req.setAttribute("level2MenuPath", currentLevel2Path);
		req.setAttribute("level3MenuPath", currentLevel3Path);
	}
	
	public MenuAuthVO getMenuAuth(String menuPath, HttpServletRequest req) {
		
		MenuAuthVO menuAuth = null;
				
		try {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			menuAuth = this.commonDao.getMenuAuth(menuPath, member.getUserDept(), member.getUserRole());
		}
		catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		
		return menuAuth;
	}
	
	private String parseUrlPathByLevel(String origin, int level) {

		String result = "";

		String []pathList = origin.split("/");

		// 요구 레벨보다 낮은 경로 Depth 를 가지고 있으면 Empty 반환
		if(pathList.length <= level) {
			return "";
		}

		// URL 경로 파싱
		for(int i = 1; i <= level; i++) {
			result += "/" + pathList[i];
		}

		return result;
	}
	
}
