package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;

public class IndexController implements Action {

	/* 로그인 인증 이후 메인화면 이동 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");

	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);

	    if (id == null) {
	        forward.setPath("/WEB-INF/view/home/home.jsp"); //  처음 진입 시 보여줄 페이지
	    } 
	    return forward;
	}


}
