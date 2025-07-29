package net.daum.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.BbsService;
import net.daum.service.BbsServiceImpl;

public class BbsDeLOKController implements Action {

	/* 자료실 삭제 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String delFolder=request.getSession().getServletContext().getRealPath("upload");
		ActionForward forward = new ActionForward();
		BbsService bbsService = new BbsServiceImpl();

		int bbs_no=Integer.parseInt(request.getParameter("bbs_no"));

		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}

		String bbs_pwd =  request.getParameter("del_pwd");

		BbsDTO db_pwd=bbsService.getBbsCont2(bbs_no);
		//게시물 번호를 기준으로 디비로 부터 비번을 가져옴.

		if(!db_pwd.getBbs_pwd().equals(bbs_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {

			if (db_pwd.getBbs_attached_file() == 7) {//기존 첨부파일이 있는 경우만
				List<FileDTO> fileList = bbsService.getFileInfo(db_pwd.getBbs_no());//번호를 기준으로 기존 첨부파일 정보를 읽어온다.

				for (FileDTO file : fileList) {
					File delFile = new File(delFolder + "/" + file.getBbs_stored_name());//삭제할 파일객체 생성
					System.out.println("삭제 시도 파일 절대 경로: " + delFile.getAbsolutePath());

					if (delFile.exists()) {//삭제할 파일이 있다면
						delFile.delete();//기본 첨부파일을 upload폴더로 부터 삭제
					}//if
				}//향상된 확장된 for

				bbsService.delFileList(bbs_no); //기존 첨부된 파일정보를 tbl_newbbs_file 테이블로 부터 삭제
			}//if

			bbsService.bbsDelete(bbs_no);//자료실 삭제

			forward.setRedirect(true);
			forward.setPath("bbs_list.do?page="+page);
			return forward;
		}
		
		return null;
	}

}
