package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/* 관리자 게시판 글쓰기 폼으로 이동시키는 컨트롤러 */
public class AdminBoardWriteController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();

		String admin_id=(String)session.getAttribute("admin_id");
		//세션 관리자 아이디를 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 된 상태
			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));     		
			}
			
			request.setAttribute("page", page);
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./WEB-INF/views/admin/admin_board_write.jsp");
			return forward;
		}
		
		return null;
	}
}
