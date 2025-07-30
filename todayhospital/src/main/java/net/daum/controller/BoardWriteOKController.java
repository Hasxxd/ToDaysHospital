package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BoardDTO;
import net.daum.service.BoardService;
import net.daum.service.BoardServiceImpl;

public class BoardWriteOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		BoardService boardService = new BoardServiceImpl();
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

		boardService.boardInsert(boarddata);

		forward.setRedirect(true);
		forward.setPath("./board_list.do");
		return forward;
	}
}
