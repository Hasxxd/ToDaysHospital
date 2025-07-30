package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class MemberJoinOKController implements Action {

	/* 회원 저장 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        MemberService memberService = new MemberServiceImpl();//업캐스팅
		
		String mem_id=request.getParameter("mem_id");
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
		
		MemberDTO member = new MemberDTO();
		
		member.setMem_id(mem_id);
		member.setMem_pwd(PwdChange.getPassWordToXEMD5String(mem_pwd));//비번 암호화
		member.setMem_name(mem_name); member.setMem_zip(mem_zip); member.setMem_zip2(mem_zip2);
		member.setMem_addr(mem_addr); member.setMem_addr2(mem_addr2); member.setMem_phone01(mem_phone01);
		member.setMem_phone02(mem_phone02); member.setMem_phone03(mem_phone03); 
		member.setMail_id(mail_id); member.setMail_domain(mail_domain);
		
		memberService.insertMember(member);//회원 저장
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);//새로운 매핑주소로 이동
		forward.setPath("member_login.do");
		return forward;		
		
	}

}
