package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class MemberEditOKController implements Action {

	/*
	 *  정보수정 완료 컨트롤러
	 */
	
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
			//request.setCharacterEncoding("UTF-8");
			//method=post로 전달되는 한글을 안깨지게 한다.
			
			String mem_pwd=request.getParameter("mem_pwd");
			String mem_name=request.getParameter("mem_name");
			String mem_zip=request.getParameter("mem_zip");
			String mem_zip2=request.getParameter("mem_zip2");
			String mem_addr=request.getParameter("mem_addr");
			String mem_addr2=request.getParameter("mem_addr2");
			String mem_phone01=request.getParameter("mem_phone01");
			String mem_phone02=request.getParameter("mem_phone02");
			String mem_phone03=request.getParameter("mem_phone03");
			String mail_id=request.getParameter("mail_id");
			String mail_domain=request.getParameter("mail_domain");
			
			MemberService memberService = new MemberServiceImpl();//업캐스팅

			MemberDTO m=new MemberDTO();
			
			m.setMem_id(id); m.setMem_pwd(PwdChange.getPassWordToXEMD5String(mem_pwd));//비번 암호화
			m.setMem_name(mem_name); m.setMem_zip(mem_zip); m.setMem_zip2(mem_zip2); m.setMem_addr(mem_addr); m.setMem_addr2(mem_addr2);
			m.setMem_phone01(mem_phone01); m.setMem_phone02(mem_phone02); m.setMem_phone03(mem_phone03); m.setMail_id(mail_id);
			m.setMail_domain(mail_domain);
			
			memberService.editMember(m);//회원정보 수정
			
			out.println("<script>");
	        out.println("alert('정보 수정했습니다!');");
	        out.println("location='member_edit.do';");
	        out.println("</script>");
		}
		
		return null;
	}

}
