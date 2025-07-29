package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.BoardDTO;
import net.daum.service.AdminBoardService;
import net.daum.service.AdminBoardServiceImpl;

public class AdminBoardContController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		AdminBoardService adminBoardService = new AdminBoardServiceImpl();

		String admin_id=(String)session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {

			int board_no=Integer.parseInt(request.getParameter("board_no"));
			String state=request.getParameter("state");

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}

			BoardDTO bc=adminBoardService.getAdminBoardCont(board_no);
			String board_cont=bc.getBoard_cont().replace("\n","<br/>");
			//textarea태그 영역에서 엔터키 친부분을 웹브라우에 출력할때 줄바꿈 처리

			request.setAttribute("b", bc);
			request.setAttribute("bcont",board_cont);
			request.setAttribute("page",page);//키,값 쌍으로 저장

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			if(state.equals("cont")) {//관리자 게시판 내용보기
				forward.setPath("./WEB-INF/views/admin/admin_board_cont.jsp");	
			}else if(state.equals("edit")) {//관리자 게시판 수정폼
				forward.setPath("./WEB-INF/views/admin/admin_board_edit.jsp");
			}	
			return forward;		
		}//if~else

		return null;
	}
}
