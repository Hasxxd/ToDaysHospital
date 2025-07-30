package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.GongjiDTO;
import net.daum.service.AdminGongjiService;
import net.daum.service.AdminGongjiServiceImpl;

//관리자 공지사항 상세정보보기, 수정폼 컨트롤러 
public class AdminGongjiContController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션객체 생성
		
		AdminGongjiService adminGongjiService = new AdminGongjiServiceImpl();//부모 인터페이스 타입으로 업캐스팅
		
		String admin_id=(String)session.getAttribute("admin_id");
		//관리자 세션 아이디를 구함

		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 후 관리자 공지사항 상세정보 보기, 수정폼
			int gongji_no=Integer.parseInt(request.getParameter("gongji_no"));
			String state=request.getParameter("state");

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}

			GongjiDTO gc=adminGongjiService.getAdminGongjiCont(gongji_no);
			String gongji_cont=gc.getGongji_cont().replace("\n","<br/>");
			//textarea태그 영역에서 엔터키 친부분을 웹브라우에 출력할때 줄바꿈 처리

			request.setAttribute("g", gc);
			request.setAttribute("g_cont",gongji_cont);
			request.setAttribute("page",page);//키,값 쌍으로 저장

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			if(state.equals("cont")) {//관리자 공지사항 내용보기
				forward.setPath("./WEB-INF/views/admin/admin_gongji_cont.jsp");	
			}else if(state.equals("edit")) {//관리자 공지사항 수정폼
				forward.setPath("./WEB-INF/views/admin/admin_gongji_edit.jsp");
			}	
			return forward;		
		}
		return null;
	}
}
