package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardWriteController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}
		request.setAttribute("page",page);//키,값 쌍으로 쪽번호 저장

		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./WEB-INF/views/board/board_write.jsp");
		return forward;
	}
}
