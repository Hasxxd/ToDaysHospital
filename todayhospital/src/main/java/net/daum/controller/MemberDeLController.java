package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;

public class MemberDeLController implements Action {

	/* 회원삭제 폼 이동 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login.do';");
			out.println("</script>");
		}else {
			MemberService memberService = new MemberServiceImpl();//업캐스팅
			
			MemberDTO m=memberService.getMember(id);//아이디에 해당하는 회원정보를 가져옴
			
			request.setAttribute("dm",m);
			
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/member/member_Del.jsp");			
			return forward;
		}
		
		return null;
	}

}
