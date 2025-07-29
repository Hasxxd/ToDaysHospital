package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.AdminMemberService;
import net.daum.service.AdminMemberServiceImpl;
import pwdconv.PwdChange;

public class AdminMemberEditOKController implements Action {

	/* 관리자에서 회원정보 수정 컨트롤러 */	
	
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
		}else {//관리자 로그인 후 회원 정보 수정
			
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
			int mem_state=Integer.parseInt(request.getParameter("mem_state"));//가입회원 1, 탈퇴 회원 2
			String mem_delcont=request.getParameter("mem_delcont");//탈퇴사유
			
			MemberDTO m=new MemberDTO();
			
			m.setMem_id(mem_id); m.setMem_pwd(PwdChange.getPassWordToXEMD5String(mem_pwd));
			m.setMem_name(mem_name); m.setMem_zip(mem_zip); m.setMem_zip2(mem_zip2); m.setMem_addr(mem_addr); m.setMem_addr2(mem_addr2);
			m.setMem_phone01(mem_phone01); m.setMem_phone02(mem_phone02); m.setMem_phone03(mem_phone03); m.setMail_id(mail_id);
			m.setMail_domain(mail_domain); m.setMem_state(mem_state); m.setMem_delcont(mem_delcont);
			
			adminMemberService.editMember(m);//관리자 회원정보 수정
			
			out.println("<script>");
	        out.println("alert('정보 수정했습니다!');");
	    	out.println("location='admin_member_info.do?state=edit&mem_id="+m.getMem_id()+"';");//주소창에 노출되는 get방식으로 ?state=edit&
	    	//mem_id=아이디 값을 담아서 전달한다. 복수개의 피라미터 이름에 get방식으로 값을 담아서 전달할 때는 &기호로 구분한다. state,mem_id 각 네임피라미터 이름에
	    	//edit와 아이디값을 저장해서 get방식으로 전달한다.
	        out.println("</script>");
	        
		}
		return null;
	}
}
