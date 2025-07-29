package net.daum.controller;

import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.AdminMemberService;
import net.daum.service.AdminMemberServiceImpl;

public class AdminMemberListController implements Action {
	
	/*
	 * 관리자 회원목록 컨트롤러 -> 페이징(쪽나누기)과 검색기능, 관리자 로그인 전과 이후를 if ~ else문으로 분기 
	 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션 객체 생성
		
		AdminMemberService adminMemberService = new AdminMemberServiceImpl();//부모 인터페이스 타입으로 업캐스팅
		
		String admin_id=(String)session.getAttribute("admin_id");
		//관리자 세션 아이디를 구함

		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 후 회원 목록
			
			int page=1;//쪽번호
			int limit=7;//한페이지에 보여지는 목록개수
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}
			String find_name=request.getParameter("find_name");//검색어
			String find_field=request.getParameter("find_field");//검색 필드
			
			MemberDTO findB=new MemberDTO();
			
			findB.setFind_field(find_field);
			findB.setFind_name("%"+find_name+"%");
			//%는 오라클 와일드 카드 문자로서 하나이상의 임의의 문자와 매핑 대응

			int listcount=adminMemberService.getMemberCount(findB);
			//검색전 전체 레코드 개수 또는 검색후 레코드 개수
			//System.out.println("총 게시물수:"+listcount+"개");

			findB.setStartrow((page-1)*7+1);//시작행번호
			findB.setEndrow(findB.getStartrow()+limit-1);//끝행번호

			List<MemberDTO> mlist=adminMemberService.getMemberList(findB);//검색전 총 회원목록 또는 검색후 회원목록			

			//총페이지수
			int maxpage=(int)((double)listcount/limit+0.95);
			//현재 페이지에 보여질 시작페이지 수(1,11,21)
			int startpage=(((int)((double)page/10+0.9))-1)*10+1;
			//현재 페이지에 보여줄 마지막 페이지 수(10,20,30)
			int endpage=maxpage;
			if(endpage > startpage+10-1) endpage=startpage+10-1;

			request.setAttribute("mlist",mlist);//mlist 키이름에 검색전,검색후 레코드 목록 저장
			request.setAttribute("page",page);
			request.setAttribute("startpage",startpage);
			request.setAttribute("endpage",endpage);
			request.setAttribute("maxpage",maxpage);
			request.setAttribute("listcount",listcount);	
			request.setAttribute("find_field",find_field);
			request.setAttribute("find_name", find_name);

			ActionForward forward=new ActionForward();
		    
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/admin/admin_member_list.jsp");////뷰페이지 폴더경로와 파일명 지정	
			return forward;				
		}
		
		return null;
	}
}
