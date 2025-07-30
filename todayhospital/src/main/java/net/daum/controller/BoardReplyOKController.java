package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BoardDTO;
import net.daum.service.BoardService;
import net.daum.service.BoardServiceImpl;

public class BoardReplyOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		BoardService boardService = new BoardServiceImpl();
		BoardDTO boarddata=new BoardDTO();

		request.setCharacterEncoding("UTF-8");

		String board_name = request.getParameter("board_name");
		String board_title = request.getParameter("board_title");
		String board_pwd = request.getParameter("board_pwd");
		String board_cont = request.getParameter("board_cont");
		int page=Integer.parseInt(request.getParameter("page"));

		boarddata.setBoard_name(board_name);
		boarddata.setBoard_title(board_title);
		boarddata.setBoard_pwd(board_pwd);		
		boarddata.setBoard_cont(board_cont);
		boarddata.setBoard_ref(Integer.parseInt(request.getParameter("board_ref")));
		boarddata.setBoard_step(Integer.parseInt(request.getParameter("board_step")));
		boarddata.setBoard_level(Integer.parseInt(request.getParameter("board_level")));

		boardService.boardReply(boarddata);

		forward.setRedirect(true);
		forward.setPath("board_list.do?page="+page);
		return forward;
	}
}
