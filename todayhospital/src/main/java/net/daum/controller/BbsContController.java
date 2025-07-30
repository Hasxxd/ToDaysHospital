package net.daum.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.BbsService;
import net.daum.service.BbsServiceImpl;

public class BbsContController implements Action {

	/*
	 * 사용자 자료실 내용보기만 조회수 증가 / 답변폼,수정폼,삭제폼 -> 조회수 증가 안함 => 컨트롤러 
	 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BbsService bbsService = new BbsServiceImpl();

		int bbs_no=Integer.parseInt(request.getParameter("bbs_no"));
		String state=request.getParameter("state");

		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));			
		}

		BbsDTO bc=null;

		if(state.equals("cont")) {//내용보기일때 만 조회수 증가
			bc=bbsService.getBbsCont(bbs_no);
		}else {//내용보기가 아닌 경우 즉 답변폼,수정폼,삭제폼일때는 조회수 증가 안함.
			bc=bbsService.getBbsCont2(bbs_no);
		}

		String bbs_cont=bc.getBbs_cont().replace("\n","<br/>");
		//textarea태그 영역에서 엔터키 친부분을 웹브라우에 출력할때 줄바꿈 처리

		if(bc.getBbs_attached_file() == 7) {//첨부파일이 있는 경우만
			List<FileDTO> fileList = bbsService.getFileInfo(bc.getBbs_no()); //번호를 기준으로 기존 첨부파일 정보를 읽어온다.

			request.setAttribute("fileList", fileList);
		}

		request.setAttribute("b", bc);
		request.setAttribute("bcont",bbs_cont);
		request.setAttribute("page",page);//키,값 쌍으로 저장 => 페이징에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능 구현을 위해서 

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		if(state.equals("cont")) {//내용보기
			forward.setPath("./WEB-INF/views/bbs/bbs_cont.jsp");		
			//board_cont.jsp 뷰페이지로 이동
		}else if(state.equals("reply")) {//답변글 폼
			forward.setPath("./WEB-INF/views/bbs/bbs_reply.jsp");			
		}else if(state.equals("edit")) {//수정폼
			forward.setPath("./WEB-INF/views/bbs/bbs_edit.jsp");
		}else if(state.equals("del")) {//삭제폼
			forward.setPath("./WEB-INF/views/bbs/bbs_del.jsp");
		}		
		return forward;		
	}

}
