package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PwdFindController implements Action {

	/*
	 *  비번찾기 공지창 폼 컨트롤러 클래스 
	 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/member/pwd_find.jsp");
		return forward;
		
	}
}
