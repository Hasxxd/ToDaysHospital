package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class MemberLoginOKController implements Action {
	
	/* 로그인 인증 컨트롤러 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//request.setCharacterEncoding("UTF-8");
		
		MemberService memberService = new MemberServiceImpl();//업캐스팅

		String id=request.getParameter("login_id");
		String pwd=request.getParameter("login_pwd");

		MemberDTO dm=memberService.loginCheck(id);//로그인 인증

		if(dm == null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다!');");
			out.println("history.back();");//뒤로 한칸이동
			out.println("</script>");
		}else {
			if(!dm.getMem_pwd().equals(PwdChange.getPassWordToXEMD5String(pwd))) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.go(-1);");//뒤로 한칸이동
				out.println("</script>");
			}else {//비번이 같은경우
				HttpSession session=request.getSession();
				//세션객체 생성
				/* 세션은 서버에서 실행되고  보안성이 우수하다.
				 * 주로 로그인 인증처리할 때 사용된다. */
				session.setAttribute("id",id);//세션아이디 키
				//이름에 아이디값 저장

				ActionForward forward=new ActionForward();
				forward.setRedirect(true);//새로운 매핑주소로
				//이동
				forward.setPath("index.do");//메인화면으로 이동
				return forward;
			}
		}
		
		return null;
	}

}
