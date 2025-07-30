package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class MemberLoginOKController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String loginId = request.getParameter("patientLoginId");
        String rawPassword = request.getParameter("patientPw");

        MemberService memberService = new MemberServiceImpl();
        MemberDTO member = memberService.loginCheck(loginId, rawPassword);

        ActionForward forward = new ActionForward();

        if (member == null) {
            request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // 로그인 실패 횟수 검사
        if (member.getLoginFailCount() >= 5) {
            request.setAttribute("msg", "로그인 5회 초과로 계정이 잠겼습니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // 계정 상태 확인
        if (!"Y".equals(member.getPatientState())) {
            request.setAttribute("msg", "비활성화된 계정입니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // 로그인 성공 → 세션 부여 및 실패횟수 초기화
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", member);

        memberService.resetLoginFailCount(loginId);

        forward.setRedirect(false);
        forward.setPath("/WEB-INF/view/total_login/total_Login/login_success.jsp");
        return forward;
    }
}