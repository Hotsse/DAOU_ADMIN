package com.daou.admin.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.admin.common.util.SHA256Util;
import com.daou.admin.login.vo.MemberVO;

/**
 * 로그인 인증 Service
 * 
 * @author hsyoon
 *
 */
@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	/**
	 * 세션 삭제
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void destroySession(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
	}
	
	/**
	 * 계정 정보 획득
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws Exception
	 */
	public MemberVO selectDaouMember(String userId, String userPw) throws Exception {
		
		MemberVO member = null;
		
		try {
			SHA256Util sha256 = new SHA256Util();
			String encryptPw = sha256.encrypt(userPw); // 입력 PW에 대해 SHA256 해싱
			
			member = this.loginDao.getDaouMember(userId, encryptPw);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	
	/**
	 * 최근 로그인 IP 획득
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String selectLastIp(String userId) throws Exception {
		
		String lastIp = null;
		
		try {
			lastIp = this.loginDao.getLastIp(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return lastIp;		
	}
	
	/**
	 * 인증 세션 생성, 최근 로그인 정보 갱신
	 * 
	 * @param member
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public boolean setMemberSession(MemberVO member, HttpServletRequest req) throws Exception {
		
		try {			
			if(member == null) throw new Exception();
			
			// 현재 IP 획득
			String currentIp = req.getRemoteAddr();
			member.setCurrentIp(currentIp);
			
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
