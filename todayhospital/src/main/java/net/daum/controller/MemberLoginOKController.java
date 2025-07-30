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

        // [1] 파라미터 수집
        String loginId = request.getParameter("patientLoginId");
        String rawPassword = request.getParameter("patientPw");

        // [2] 서비스 계층 호출
        MemberService memberService = new MemberServiceImpl();
        MemberDTO member = memberService.loginCheck(loginId);

        ActionForward forward = new ActionForward();

        // [3] 로그인 검증
        if (member == null) {
            request.setAttribute("msg", "가입되지 않은 회원입니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // [4] 비밀번호 비교
        String encryptedPassword = PwdChange.getSha512(rawPassword);
        if (!encryptedPassword.equals(member.getPatientPw())) {
            request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // [5] 계정 상태 확인
        if (!"Y".equals(member.getPatientState())) {
            request.setAttribute("msg", "현재 사용할 수 없는 계정입니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // [6] 로그인 실패 횟수 제한
        if (member.getLoginFailCount() >= 5) {
            request.setAttribute("msg", "로그인 실패 횟수 초과로 잠긴 계정입니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // [7] 로그인 성공 → 세션 부여
        HttpSession session = request.getSession();
        session.setAttribute("id", member.getPatientLoginId());

        forward.setRedirect(false);
        forward.setPath("/WEB-INF/view/total_login/total_Login/login_success.jsp");
        return forward;
    }
}