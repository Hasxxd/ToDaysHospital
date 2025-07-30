package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.BoardDTO;
import net.daum.service.AdminBoardService;
import net.daum.service.AdminBoardServiceImpl;

/* 관리자 게시판 저장 컨트롤러 클래스 */
public class AdminBoardWriteOKController implements Action {

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
		}else {//관리자 로그인 된 상태			
			
			BoardDTO boarddata=new BoardDTO();
			ActionForward forward=new ActionForward();

			//request.setCharacterEncoding("UTF-8");//method=post로 전달되는 한글을 서버에서 받을 때 안깨지게 한다.

			String board_name = request.getParameter("board_name");
			String board_title = request.getParameter("board_title");
			String board_pwd = request.getParameter("board_pwd");		
			String board_cont = request.getParameter("board_cont");

			boarddata.setBoard_name(board_name);
			boarddata.setBoard_title(board_title);
			boarddata.setBoard_pwd(board_pwd);
			boarddata.setBoard_cont(board_cont);

			adminBoardService.boardInsert(boarddata);

			forward.setRedirect(true);
			forward.setPath("admin_board_list.do");
			return forward;
		}
		return null;
	}
}
