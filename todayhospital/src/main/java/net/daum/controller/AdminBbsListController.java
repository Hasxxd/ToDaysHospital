package net.daum.controller;

import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.BbsDTO;
import net.daum.service.AdminBbsService;
import net.daum.service.AdminBbsServiceImpl;

public class AdminBbsListController implements Action {

	/* 관리자 단 자료실 목록보기 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션 객체
		//생성
		
		AdminBbsService adminBbsService = new AdminBbsServiceImpl();//부모 인터페이스 타입으로 업캐스팅
		
		String admin_id=(String)session.getAttribute("admin_id");
		//관리자 세션 아이디를 구함

		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 후 게시판 목록
			int page=1;//쪽번호
			int limit=7;//한페이지에 보여지는 목록개수
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}
			String find_name=request.getParameter("find_name");//검색어
			String find_field=request.getParameter("find_field");//검색
			//필드
			
			BbsDTO findB=new BbsDTO();
			
			findB.setFind_field(find_field);
			findB.setFind_name("%"+find_name+"%");
			//%는 오라클 와일드 카드 문자로서 하나이상의 임의의 문자와
			//매핑 대응
			
			int totalCount= adminBbsService.getRowCount(findB);//검색전 총레코드 개수,검색후 레코드 개수

			findB.setStartrow((page-1)*7+1);//시작행번호
			findB.setEndrow(findB.getStartrow()+limit-1);//끝행 번호

			List<BbsDTO> blist=adminBbsService.getBbsList(findB); //검색 전후 목록

			//총 페이지수
			int maxpage=(int)((double)totalCount/limit+0.95);
			//시작페이지(1,11,21 ..)
			int startpage=(((int)((double)page/10+0.9))-1)*10+1;
			//현재 페이지에 보여질 마지막 페이지(10,20 ..)
			int endpage=maxpage;
			if(endpage>startpage+10-1) endpage=startpage+10-1;
			

			request.setAttribute("blist",blist);//blist문자열 속성 키이름에 자료실 목록을 저장
			request.setAttribute("page",page);//쪽번호
			request.setAttribute("startpage",startpage);//시작페이지
			request.setAttribute("endpage",endpage);//마지막 페이지
			request.setAttribute("maxpage",maxpage);//최대 페이지
			request.setAttribute("listcount",totalCount);//검색전후 레코드 개수
			request.setAttribute("find_field",find_field);//검색 필드
			request.setAttribute("find_name",find_name);//검색어
			
			ActionForward forward=new ActionForward();
			forward.setPath("/WEB-INF/views/admin/admin_bbs_list.jsp");//뷰페이지 경로 설정
			forward.setRedirect(false);
			return forward;		
		}//if else
		return null;
	}

}
