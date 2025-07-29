package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.GongjiDTO;
import net.daum.service.AdminGongjiService;
import net.daum.service.AdminGongjiServiceImpl;

public class AdminGongjiWriteOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션 객체
		//생성
		
		AdminGongjiService adminGongjiService = new AdminGongjiServiceImpl();//부모 인터페이스 타입으로 업캐스팅
		
		String admin_id=(String)session.getAttribute("admin_id");
		//관리자 세션 아이디를 구함

		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 후 관리자 공지사항 저장
			
			GongjiDTO g=new GongjiDTO();
			ActionForward forward=new ActionForward();

			//request.setCharacterEncoding("UTF-8");//method=post로 전달되는 한글을 서버에서 받을 때 안깨지게 한다.

			String gongji_name = request.getParameter("gongji_name");
			String gongji_title = request.getParameter("gongji_title");
			String gongji_pwd = request.getParameter("gongji_pwd");		
			String gongji_cont = request.getParameter("gongji_cont");

			g.setGongji_name(gongji_name);
			g.setGongji_title(gongji_title);
			g.setGongji_pwd(gongji_pwd);
			g.setGongji_cont(gongji_cont);

			adminGongjiService.adminGongjiInsert(g);

			forward.setRedirect(true);
			forward.setPath("admin_gongji_list.do");
			return forward;
		}
		return null;
	}
}
