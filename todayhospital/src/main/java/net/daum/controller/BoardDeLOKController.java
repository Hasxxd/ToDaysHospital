package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BoardDTO;
import net.daum.service.BoardServiceImpl;

public class BoardDeLOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		ActionForward forward = new ActionForward();
		BoardServiceImpl boardService=new BoardServiceImpl();

		int board_no=Integer.parseInt(request.getParameter("board_no"));

		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}

		String board_pwd =  request.getParameter("del_pwd");

		BoardDTO db_pwd=boardService.getBoardCont(board_no);
		//게시물 번호를 기준으로 디비로 부터 비번을 가져옴.

		if(!db_pwd.getBoard_pwd().equals(board_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {

			boardService.boardDelete(board_no);

			forward.setRedirect(true);
			forward.setPath("board_list.do?page="+page);
			return forward;
		}
		return null;
	}
}
