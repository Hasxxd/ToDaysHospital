package net.daum.controller;

import java.io.PrintWriter;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class PwdFindOKController implements Action {

	/*
	 *  비번찾기 결과 컨트롤러 
	 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		MemberService memberService = new MemberServiceImpl();//업캐스팅
        MemberDTO m=new MemberDTO();
        ActionForward forward=new ActionForward();
        
		//request.setCharacterEncoding("UTF-8");
		
		String pwd_id=request.getParameter("pwd_id");
		String pwd_name=request.getParameter("pwd_name");
		
		m.setMem_id(pwd_id); m.setMem_name(pwd_name);
		
		MemberDTO pm=memberService.pwdMember(m);
		//아이디와 회원이름을 기준으로 디비로 부터 회원정보 검색
		
		if(pm==null) {
			out.println("<script>");
			out.println("alert('회원정보를 찾을 수 없습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			Random r=new Random();
			int pwd_random=r.nextInt(100000);//0이상 십만 미만 사이의 정수 숫자 난수를 발생
			String ran_pwd=Integer.toString(pwd_random);//임시 정수 비번을 문자열로 변경
			m.setMem_pwd(PwdChange.getPassWordToXEMD5String(ran_pwd));//임시 비번 암호화

			memberService.updatePwd(m);//오라클 DB의 비번을 암호화된 임시비번으로 수정
			/* 문제) 암호화 된 임시 비번으로 수정되게 한다. update 아이디명은 p_edit로 한다. 그리고 개발자 테스트까지 완료
			 * 해 보자.
			 */
			
			request.setAttribute("ran_pwd",ran_pwd);
			forward.setRedirect(false);
			forward.setPath("./WEB-INF/views/member/pwd_find_ok.jsp");
			return forward;
		}
		
		return null;
	}

}
