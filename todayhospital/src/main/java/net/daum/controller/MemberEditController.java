package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;

public class MemberEditController implements Action {

	/* 회원정보 수정폼 이동 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션객체 생성

		MemberService memberService = new MemberServiceImpl();//업캐스팅

		String id=(String)session.getAttribute("id"); //세션 아이디값을 구함
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login.do';");
			out.println("</script>");
		}else {
			String[] phone= {"010","011"};
			String[] email= {"naver.com","daum.net","gmail.com","직접입력"};
			request.setAttribute("phone",phone);
			request.setAttribute("email",email);
			
			MemberDTO m=memberService.getMember(id);//DB로부터 아이디에 해당하는 회원정보를 가져옴.
			
			request.setAttribute("em",m);
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/member/member_Edit.jsp");
			return forward;
		}
		
		return null;
	}

}
