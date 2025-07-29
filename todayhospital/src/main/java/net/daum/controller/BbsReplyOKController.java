package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.BbsDTO;
import net.daum.service.BbsService;
import net.daum.service.BbsServiceImpl;

public class BbsReplyOKController implements Action {

	/* 답변 달기 저장 컨트롤러 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BbsService bbsService = new BbsServiceImpl();
		BbsDTO bbsdata=new BbsDTO();

		String bbs_name = request.getParameter("bbs_name");
		String bbs_title = request.getParameter("bbs_title");
		String bbs_pwd = request.getParameter("bbs_pwd");
		String bbs_cont = request.getParameter("bbs_cont");
		int page=Integer.parseInt(request.getParameter("page"));

		bbsdata.setBbs_name(bbs_name);
		bbsdata.setBbs_title(bbs_title);
		bbsdata.setBbs_pwd(bbs_pwd);		
		bbsdata.setBbs_cont(bbs_cont);
		bbsdata.setBbs_ref(Integer.parseInt(request.getParameter("bbs_ref"))); //글그룹번호
		bbsdata.setBbs_step(Integer.parseInt(request.getParameter("bbs_step"))); //원본글과 답변글을 구분하는 번호
		bbsdata.setBbs_level(Integer.parseInt(request.getParameter("bbs_level"))); //답변글 정렬순서

		bbsService.bbsReply(bbsdata);

		forward.setRedirect(true);
		forward.setPath("bbs_list.do?page="+page);
		return forward;
		
	}

}
