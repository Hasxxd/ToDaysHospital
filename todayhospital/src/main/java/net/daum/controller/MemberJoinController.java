package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberJoinController implements Action {

	/* 서블릿 MVC 회원가입 컨트롤러 클래스 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] phone= {"010","011"};
		String[] email= {"naver.com","daum.net","gmail.com","직접입력"};
		request.setAttribute("phone",phone);
		request.setAttribute("email",email);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/member/member_Join.jsp");
		return forward;
	}
}
