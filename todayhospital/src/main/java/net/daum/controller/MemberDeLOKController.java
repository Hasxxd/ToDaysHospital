package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class MemberDeLOKController implements Action {

	/* 회원탈퇴 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");

		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login.do';");
			out.println("</script>");
		}else {
			MemberService memberService = new MemberServiceImpl();//업캐스팅

			//request.setCharacterEncoding("utf-8");
			
			String del_pwd=request.getParameter("del_pwd");
			String del_cont=request.getParameter("del_cont");
			
			MemberDTO db_pwd=memberService.getMember(id);
			
			if(!db_pwd.getMem_pwd().equals(PwdChange.getPassWordToXEMD5String(del_pwd))) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				MemberDTO dm=new MemberDTO();
				dm.setMem_id(id); dm.setMem_delcont(del_cont);
				memberService.delMem(dm);//회원탈퇴
				
				session.invalidate();//세션만료 => 로그아웃

				out.println("<script>");
				out.println("alert('회원 탈퇴 했습니다!');");
				out.println("location='member_login.do';");
				out.println("</script>");
			}
		}
		
		return null;
	}

}
