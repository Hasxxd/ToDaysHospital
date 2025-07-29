package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BoardDTO;
import net.daum.service.BoardServiceImpl;

public class BoardEditOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		ActionForward forward = new ActionForward();
		BoardServiceImpl boardService=new BoardServiceImpl();
		BoardDTO eb=new BoardDTO();

		request.setCharacterEncoding("utf-8");

		int board_no=Integer.parseInt(request.getParameter("board_no"));

		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}

		String board_pwd =  request.getParameter("board_pwd");
		String board_name = request.getParameter("board_name");
		String board_title = request.getParameter("board_title");
		String board_cont = request.getParameter("board_cont");

		BoardDTO db_pwd=boardService.getBoardCont(board_no);
		//게시물 번호를 기준으로 디비로 부터 비번을 가져옴.
		
		if(!db_pwd.getBoard_pwd().equals(board_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			eb.setBoard_no(board_no);
			eb.setBoard_name(board_name);
			eb.setBoard_title(board_title);
			eb.setBoard_cont(board_cont);
			
			boardService.editBoard(eb);//게시물 수정			

			forward.setRedirect(true);
			forward.setPath("board_cont.do?board_no="+board_no+"&page="+page+"&state=cont");//?뒤에 3개의 인자값이 get방식으로 내용보기로 이동.
			return forward;				 
		}
		return null;
	}
}
