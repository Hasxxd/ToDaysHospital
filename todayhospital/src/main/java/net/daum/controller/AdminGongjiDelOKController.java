package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.service.AdminGongjiService;
import net.daum.service.AdminGongjiServiceImpl;

public class AdminGongjiDelOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		AdminGongjiService adminGongjiService = new AdminGongjiServiceImpl();	

		String admin_id=(String)session.getAttribute("admin_id");
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 관리자로 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {
			int gongji_no=Integer.parseInt(request.getParameter("gongji_no"));

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}
			
			adminGongjiService.deleteAdminGongji(gongji_no);//관리자 공지사항 삭제
			/* 문제풀이)
			 *  1.번호를 기준으로 해당 게시물을 삭제한다.
			 */
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_gongji_list.do?page="+page);			
			return forward;				
		}		  
		return null;
	}
}
