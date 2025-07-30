package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.AdminMemberService;
import net.daum.service.AdminMemberServiceImpl;

public class AdminMemberInfoController implements Action {

	/* 관리자 회원 상세 정보 보기 컨트롤러 */
	
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
		}else {//관리자 로그인 후 회원 상세정보와 수정폼
			
			String mem_id=request.getParameter("mem_id");
			String state=request.getParameter("state");

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}

			MemberDTO m=adminMemberService.getAdminMemberInfo(mem_id);//아이디에 해당하는 회원정보 가져오기
			
			String del_cont=null;
			if(m.getMem_delcont() != null) {//탈퇴 사유가 있는 경우
				del_cont=m.getMem_delcont().replace("\n","<br/>");
				//textarea 영역에서 엔터키 친부분을 웹브라우에 출력할때 다음줄로 줄바꿈
			}
			
			String[] phone = {"010","011","019"};
			String[] email = {"naver.com","daum.net","gmail.com","직접입력"};
			request.setAttribute("phone",phone);
			request.setAttribute("email", email);
			request.setAttribute("m", m);
			request.setAttribute("del_cont",del_cont);
			request.setAttribute("page",page);//키,값 쌍으로 저장			

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);//기존 매핑주소를 유지하면서 request.setAttribute(키이름,값)도 잃어 버리지 않고 유지한다.

			if(state.equals("info")) {//관리자 회원관리 상세정보보기
				forward.setPath("./WEB-INF/views/admin/admin_member_info.jsp");	//뷰페이지 경로 설정
			}else if(state.equals("edit")) {//관리자 회원관리 수정폼
				forward.setPath("./WEB-INF/views/admin/admin_member_edit.jsp");
			}	
			return forward;		
		}
		return null;
	}
}
