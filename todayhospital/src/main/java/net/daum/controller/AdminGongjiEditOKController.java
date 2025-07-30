package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.GongjiDTO;
import net.daum.service.AdminGongjiService;
import net.daum.service.AdminGongjiServiceImpl;

/* 관리자 공지사항 수정 완료 컨트롤러 */
public class AdminGongjiEditOKController implements Action {

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
		}else {//관리자 로그인 후 관리자 공지사항 수정완료
			
			GongjiDTO eg=new GongjiDTO();

			int gongji_no=Integer.parseInt(request.getParameter("gongji_no"));

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}
			
			String gongji_name = request.getParameter("gongji_name");
			String gongji_title = request.getParameter("gongji_title");
			String gongji_pwd =  request.getParameter("gongji_pwd");
			String gongji_cont = request.getParameter("gongji_cont");

			eg.setGongji_no(gongji_no);
			eg.setGongji_name(gongji_name);
			eg.setGongji_title(gongji_title);
			eg.setGongji_pwd(gongji_pwd);
			eg.setGongji_cont(gongji_cont);

			adminGongjiService.editAdminGongji(eg);//관리자 공지사항 수정			

			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_gongji_cont.do?gongji_no="+gongji_no+"&page="+page+"&state=cont");
			//?뒤에 3개의 인자값이 get방식으로 관리자 내용보기로 이동.
			return forward;		
		}
		return null;
	}
}
