package net.daum.controller;

import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.AdminBbsService;
import net.daum.service.AdminBbsServiceImpl;

public class AdminBbsContController implements Action {
	
	/* 관리자 자료실 상세정보 보기와 수정폼 컨트롤러 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		AdminBbsService adminBbsService = new AdminBbsServiceImpl();

		String admin_id=(String)session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 된 상태	
			
			int bbs_no=Integer.parseInt(request.getParameter("bbs_no"));
			String state=request.getParameter("state");

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}

			BbsDTO bc=adminBbsService.getAdminBbsCont(bbs_no);			
					
			String bbs_cont=bc.getBbs_cont().replace("\n","<br/>");
			//textarea태그 영역에서 엔터키 친부분을 웹브라우에 출력할때 줄바꿈 처리

			if(bc.getBbs_attached_file() == 7) {//첨부파일이 있는 경우만
				List<FileDTO> fileList = adminBbsService.getFileInfo(bc.getBbs_no()); //번호를 기준으로 기존 첨부파일 정보를 읽어온다.
				
				request.setAttribute("fileList", fileList);
			}
			
			request.setAttribute("b", bc);
			request.setAttribute("bcont",bbs_cont);
			request.setAttribute("page",page);//키,값 쌍으로 저장
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			if(state.equals("cont")) {//내용보기
				forward.setPath("./WEB-INF/views/admin/admin_bbs_cont.jsp");		
				//board_cont.jsp 뷰페이지로 이동
			}else if(state.equals("edit")) {//수정폼
				forward.setPath("./WEB-INF/views/admin/admin_bbs_edit.jsp");
			}		
			return forward;		
		}		
		return null;
	}
}
