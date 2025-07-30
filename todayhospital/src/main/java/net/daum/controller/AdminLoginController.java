package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminLoginController implements Action {

	/*
	 * 관리자 로그인 폼 이동 컨트롤러 
	 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/total_login/total_Login/admin_login.jsp");
		return forward;		
	}
}
