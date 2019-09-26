package com.daou.admin.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.admin.common.util.SHA256Util;
import com.daou.admin.login.vo.MemberVO;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	public void destroySession(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
	}
	
	public MemberVO selectDaouMember(String userId, String userPw) throws Exception {
		
		MemberVO member = null;
		
		try {
			SHA256Util sha256 = new SHA256Util();
			String encryptPw = sha256.encrypt(userPw);
			
			member = this.loginDao.selectDaouMember(userId, encryptPw);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	public String selectLastIp(String userId) throws Exception {
		
		String lastIp = null;
		
		try {
			lastIp = this.loginDao.selectLastIp(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return lastIp;		
	}
	
	public boolean setMemberSession(MemberVO member, HttpServletRequest req) throws Exception {
		
		try {			
			if(member == null) throw new Exception();
			
			// 현재 IP 획득
			String currentIp = req.getRemoteAddr();
			member.setCurrentIp(currentIp);
			
			System.out.println(member.toString());
			
			// 세션 등록
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			
			// 로그인 정보 기록
			this.loginDao.updateCurrentLoginInfo(member.getUserId(), member.getCurrentIp());
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
