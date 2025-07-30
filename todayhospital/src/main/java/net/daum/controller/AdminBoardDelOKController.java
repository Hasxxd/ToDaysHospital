package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.service.AdminBoardService;
import net.daum.service.AdminBoardServiceImpl;

public class AdminBoardDelOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		AdminBoardService adminBoardService = new AdminBoardServiceImpl();	

		String admin_id=(String)session.getAttribute("admin_id");
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 관리자로 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {
			int board_no=Integer.parseInt(request.getParameter("board_no"));

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}
			
			adminBoardService.deleteAdminBoard(board_no);//관리자 게시판 삭제
			/* 문제풀이)
			 *  1.번호를 기준으로 해당 게시물을 삭제한다.
			 */
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_board_list.do?page="+page);			
			return forward;				
		}		  
		return null;
	}
}
