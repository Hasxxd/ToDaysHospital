package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BoardDTO;
import net.daum.service.BoardService;
import net.daum.service.BoardServiceImpl;


public class BoardContController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardService boardService = new BoardServiceImpl();   	

		int board_no=Integer.parseInt(request.getParameter("board_no"));
		String state=request.getParameter("state");

		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}

		if(state.equals("cont")) {//내용보기일때만 조회수 증가
			boardService.updateHit(board_no);
		}		
		BoardDTO bc=boardService.getBoardCont(board_no);
		String board_cont=bc.getBoard_cont().replace("\n","<br/>");
		//textarea태그 영역에서 엔터키 친부분을 웹브라우에 출력할때 줄바꿈 처리

		request.setAttribute("b", bc);
		request.setAttribute("bcont",board_cont);
		request.setAttribute("page",page);//키,값 쌍으로 저장
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(state.equals("cont")) {//내용보기
			forward.setPath("./WEB-INF/views/board/board_cont.jsp");		
			//board_cont.jsp 뷰페이지로 이동
		}else if(state.equals("reply")) {//답변글 폼
			forward.setPath("./WEB-INF/views/board/board_reply.jsp");			
		}else if(state.equals("edit")) {//수정폼
			forward.setPath("./WEB-INF/views/board/board_edit.jsp");
		}else if(state.equals("del")) {//삭제폼
			forward.setPath("./WEB-INF/views/board/board_del.jsp");
		}		
		return forward;		
	}
}
