package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.service.AdminMemberService;
import net.daum.service.AdminMemberServiceImpl;


public class AdminMemberDelOKController implements Action {

	/* 관리자에서 회원삭제 컨트롤러 */
	
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
		}else {//관리자 로그인 후 회원 삭제
			
			String mem_id=request.getParameter("mem_id");
			
			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));//get으로 전달된 쪽번호를 가져와서 정수 숫자로 변경해서 저장    		
			}
			
			adminMemberService.delMember(mem_id);//회원삭제
						
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_member_list.do?page="+page);	//*.do?page=쪽번호가 페이징 즉 쪽나누기에서 get방식으로 전달하면 내가 본 페이지번호 바로
			//이동하는 책갈피 기능이 구현된다.
			return forward;	
		}
		return null;
	}
}
