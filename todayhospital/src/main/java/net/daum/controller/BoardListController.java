package net.daum.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dao.BoardDAOImpl;
import net.daum.dto.BoardDTO;
import net.daum.service.BoardService;
import net.daum.service.BoardServiceImpl;

public class BoardListController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BoardService boardService = new BoardServiceImpl();
		
		int page=1;//현재 쪽번호
		int limit=10;//한페이지에 보여지는 목록개수
				
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
			//get방식으로 넘어온 쪽번호를 정수형 숫자로 바꿔서 좌측변수에 저장한다.
		}
		
		String find_field=request.getParameter("find_field");//검색어
		String find_name=request.getParameter("find_name");//검색필드			
		
		BoardDTO findB=new BoardDTO();
		findB.setFind_field(find_field);
		findB.setFind_name("%"+find_name+"%");//%는 오라클 와일드 카드 문자로서 하나이상의 임의의 문자와 매핑 대응
				
		int listcount= boardService.getListCount(findB);//검색 전 총 레코드 개수, 검색후 레코드 개수
		
		findB.setStartrow((page-1)*10+1);//시작행번호
		findB.setEndrow(findB.getStartrow()+limit-1);//끝행번호
		
		List<BoardDTO> blist= boardService.getBoardList(findB);//검색 전 총 목록, 검색후 목록
		
		//총 페이지 수.
		int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리.
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;		
		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
		int endpage = maxpage;		   		
		if (endpage>startpage+10-1) endpage=startpage+10-1;
		
		request.setAttribute("blist", blist);
		request.setAttribute("page", page);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("listcount",listcount);
		request.setAttribute("find_field",find_field);
		request.setAttribute("find_name",find_name);
		
		ActionForward forward=new ActionForward();
	    
		forward.setRedirect(false);
		forward.setPath("./WEB-INF/views/board/board_list.jsp");
		return forward;
	}
}
