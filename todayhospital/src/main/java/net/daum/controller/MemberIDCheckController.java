package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;

public class MemberIDCheckController implements Action {
	
	/* 아이디 중복 검색 컨트롤러 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out=response.getWriter();
	    MemberService memberService = new MemberServiceImpl();//업캐스팅
	    
	    String id=request.getParameter("id");//회원아이디
	    
	    MemberDTO db_id=memberService.idCheck(id);
	    
	    int re=-1;//중복아이디가 없는 경우 반환값
	    if(db_id != null) {//중복아이디가 있는경우
	    	re=1;
	    }
	    out.println(re);//값 반환
	    
		return null;
	}
}
