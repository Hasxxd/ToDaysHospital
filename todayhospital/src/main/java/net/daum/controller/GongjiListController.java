package net.daum.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.GongjiDTO;
import net.daum.service.GongjiService;
import net.daum.service.GongjiServiceImpl;

public class GongjiListController implements Action {

	/* 사용자 공지사항 페이징 안되는 목록 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GongjiService gongjiService = new GongjiServiceImpl();
		
		List<GongjiDTO> glist=gongjiService.getGongjiList();//사용자 공지목록

		request.setAttribute("glist", glist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./WEB-INF/views/gongji/gongji_list.jsp");
		return forward;
	}
}
