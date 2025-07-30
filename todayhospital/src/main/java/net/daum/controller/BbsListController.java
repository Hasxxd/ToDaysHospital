package net.daum.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BbsDTO;
import net.daum.service.BbsService;
import net.daum.service.BbsServiceImpl;

public class BbsListController implements Action {

	/* 사용자 자료실 목록 -> 페이징(검색전 페이징 총목록/검색후 페이징 목록 과 검색전후 레코드 개수)과 검색기능 추가 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int page=1;
		int limit=10;//한페이지에 보여지는 목록개수
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));//페이지번호를 정수숫자로 변경해서 저장         
		}
		String find_name=request.getParameter("find_name");//검색어
		String find_field=request.getParameter("find_field");//검색 필드
		
		BbsDTO findB=new BbsDTO();
		BbsService bbsService = new BbsServiceImpl();
		
		findB.setFind_field(find_field);
		findB.setFind_name("%"+find_name+"%");
		//SQL문에서 %는 검색에서 하나이상의 모르는 임의의 문자와 매핑 대응하는 와일드 카드문자

		int totalCount= bbsService.getRowCount(findB);//검색전 총레코드 개수,검색후 레코드 개수

		findB.setStartrow((page-1)*10+1);//시작행번호
		findB.setEndrow(findB.getStartrow()+limit-1);//끝행 번호

		List<BbsDTO> blist=bbsService.getBbsList(findB); //검색 전후 목록

		//총 페이지수
		int maxpage=(int)((double)totalCount/limit+0.95);
		//시작페이지(1,11,21 ..)
		int startpage=(((int)((double)page/10+0.9))-1)*10+1;
		//현재 페이지에 보여질 마지막 페이지(10,20 ..)
		int endpage=maxpage;
		if(endpage>startpage+10-1) endpage=startpage+10-1;
		

		request.setAttribute("blist",blist);//blist문자열 속성 키이름에 자료실 목록을 저장
		request.setAttribute("page",page);//쪽번호 -> 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능 구현
		request.setAttribute("startpage",startpage);//시작페이지
		request.setAttribute("endpage",endpage);//마지막 페이지
		request.setAttribute("maxpage",maxpage);//최대 페이지
		request.setAttribute("listcount",totalCount);//검색전후 레코드 개수
		request.setAttribute("find_field",find_field);//검색 필드
		request.setAttribute("find_name",find_name);//검색어
		
		ActionForward forward=new ActionForward();
		forward.setPath("/WEB-INF/views/bbs/bbs_list.jsp");//뷰페이지 경로 설정
		forward.setRedirect(false);
		return forward;				
	}
}
