package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AdminIndexController implements Action {
	
	/*
	 * 관리자 로그인 인증후 관리자 메인페이지로 이동시키는 컨트롤러
	 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		String admin_id = (String)session.getAttribute("admin_id");//관리자 세션 아이디를 구함
		System.out.println(admin_id);
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/admin/admin_index.jsp");
			return forward;
		}
		return null;
	}
}
