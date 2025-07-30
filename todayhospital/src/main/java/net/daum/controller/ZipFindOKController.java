package net.daum.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.ZipcodeDTO;
import net.daum.dto.ZipcodeDTO2;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;

public class ZipFindOKController implements Action {

	/* 오라클 DB로 부터 우편주소 검색 결과 컨트롤러 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberService memberService = new MemberServiceImpl();//업캐스팅

		ActionForward forward=new ActionForward();

		//request.setCharacterEncoding("utf-8");
		String dong=request.getParameter("dong").trim();

		List<ZipcodeDTO> zlist=memberService.zipFind("%"+dong+"%");
		//%는 오라클 와일드 카드로 하나이상의 임의의 문자와 매핑
		//대응한다.
		List<ZipcodeDTO2> zlist2=new ArrayList<>();

		for(ZipcodeDTO z:zlist) {
			ZipcodeDTO2 z2=new ZipcodeDTO2();

			z2.setZipcode(z.getZipcode());//우편번호
			z2.setAddr(z.getSido()+" "+z.getGugun()+" "+z.getDong());
			//시도 구군 동을 저장
			zlist2.add(z2);//컬렉션에 추가
		}
		request.setAttribute("zipcodelist",zlist2);//zipcodelist
		//키이름에 가공된 주소목록이 저장됨.
		request.setAttribute("dong",dong);

		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/member/zip_find.jsp");
		return forward;
	}

}
