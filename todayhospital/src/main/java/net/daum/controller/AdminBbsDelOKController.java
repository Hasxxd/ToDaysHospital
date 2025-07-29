package net.daum.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.AdminBbsService;
import net.daum.service.AdminBbsServiceImpl;

public class AdminBbsDelOKController implements Action {

	/* 관리자 자료실 삭제 컨트롤러 클래스 */
	
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
			String delFolder=request.getSession().getServletContext().getRealPath("upload");


			int bbs_no=Integer.parseInt(request.getParameter("bbs_no"));

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}

			BbsDTO db_bbs=adminBbsService.getAdminBbsCont(bbs_no);
			//게시물 번호를 기준으로 디비로 부터 자료실 정보를 가져옴.

			if (db_bbs.getBbs_attached_file() == 7) {//기존 첨부파일이 있는 경우만
				List<FileDTO> fileList = adminBbsService.getFileInfo(db_bbs.getBbs_no());//번호를 기준으로 기존 첨부파일 정보를 읽어온다.

				for (FileDTO file : fileList) {
					File delFile = new File(delFolder + "/" + file.getBbs_stored_name());//삭제할 파일객체 생성
					System.out.println("삭제 시도 파일 절대 경로: " + delFile.getAbsolutePath());

					if (delFile.exists()) {//삭제할 파일이 있다면
						delFile.delete();//기본 첨부파일을 upload폴더로 부터 삭제
					}//if
				}//향상된 확장된 for

				adminBbsService.delFileList(bbs_no); ////기존 첨부된 파일정보를 tbl_newbbs_file 테이블로 부터 삭제
			}//if

			adminBbsService.adminBbsDelete(bbs_no);//자료실 삭제

			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_bbs_list.do?page="+page);
			return forward;
		}

		return null;
	}
}
