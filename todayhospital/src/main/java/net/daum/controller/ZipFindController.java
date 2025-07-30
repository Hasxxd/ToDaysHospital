package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ZipFindController implements Action {

	/* 우편검색 공지창 띄우는 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/member/zip_find.jsp");
		return forward;
	}
}
