package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.BoardDTO;
import net.daum.service.AdminBoardService;
import net.daum.service.AdminBoardServiceImpl;

public class AdminBoardEditOKController implements Action {

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
			BoardDTO eb=new BoardDTO();
			
			int board_no=Integer.parseInt(request.getParameter("board_no"));

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}

			String board_pwd =  request.getParameter("board_pwd");
			String board_name = request.getParameter("board_name");
			String board_title = request.getParameter("board_title");
			String board_cont = request.getParameter("board_cont");


			eb.setBoard_no(board_no);
			eb.setBoard_name(board_name);
			eb.setBoard_title(board_title);
			eb.setBoard_pwd(board_pwd);
			eb.setBoard_cont(board_cont);

			adminBoardService.editAdminBoard(eb);//게시물 수정			

			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_board_cont.do?board_no="+board_no+"&page="+page+"&state=cont");
			//?뒤에 3개의 인자값이 get방식으로 내용보기로 이동.
			return forward;		
		}
		return null;
	}
}
