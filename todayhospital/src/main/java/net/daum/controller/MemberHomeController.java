package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;

public class MemberHomeController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();

        HttpSession session = request.getSession(false);
        MemberDTO loginUser = (session != null) ? (MemberDTO) session.getAttribute("loginUser") : null;

        if (loginUser == null) {
            // 로그인되지 않은 사용자 → 비회원 메인으로 리디렉션
            forward.setRedirect(true);
            forward.setPath("/WEB-INF/view/member/index_home.jsp");
            return forward;
        }

        // 로그인된 사용자 → 회원 전용 홈 화면으로 이동
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/view/member/member_home.jsp");
        return forward;
    }
}
