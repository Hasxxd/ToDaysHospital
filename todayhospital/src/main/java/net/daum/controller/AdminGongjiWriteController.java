package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.service.AdminGongjiService;
import net.daum.service.AdminGongjiServiceImpl;

public class AdminGongjiWriteController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션 객체
		//생성
		
		AdminGongjiService adminGongjiService = new AdminGongjiServiceImpl();//부모 인터페이스 타입으로 업캐스팅
		
		String admin_id=(String)session.getAttribute("admin_id");
		//관리자 세션 아이디를 구함

		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 후 관리자 공지사항 글쓰기 폼 
			
			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			request.setAttribute("page", page);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./WEB-INF/views/admin/admin_gongji_write.jsp");
			return forward;
		}//if ~ else
		return null;
	}
}
