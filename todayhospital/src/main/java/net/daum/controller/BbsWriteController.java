package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BbsWriteController implements Action {

	 /* 사용자 자료실 글쓰기 폼으로 이동하기 위한 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}
		request.setAttribute("page",page);//키,값 쌍으로 쪽번호 저장 -> 페이징에서 내가 본 쪽번호로 바로 이동하기 위한 채갈피 기능을 구현하기 위해서

		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./WEB-INF/views/bbs/bbs_write.jsp");
		return forward;
	}
}
