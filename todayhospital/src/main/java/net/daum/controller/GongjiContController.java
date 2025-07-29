package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.GongjiDTO;
import net.daum.service.GongjiService;
import net.daum.service.GongjiServiceImpl;

public class GongjiContController implements Action {

	/* 사용자 공지내용 보기 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GongjiService gongjiService = new GongjiServiceImpl();//부모 인터페이스 타입으로 업캐스팅
		
		int gongji_no=Integer.parseInt(request.getParameter("gongji_no"));
		
		GongjiDTO gc=gongjiService.getGCont(gongji_no);
		String g_cont=gc.getGongji_cont().replace("\n","<br/>");
		
		request.setAttribute("g", gc);
		request.setAttribute("g_cont",g_cont);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/gongji/gongji_cont.jsp");
		return forward;
	}
}
