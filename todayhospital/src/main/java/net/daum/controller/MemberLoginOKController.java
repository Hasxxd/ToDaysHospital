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

        String id = request.getParameter("patientLoginId");
        String pwd = request.getParameter("patientPw");

        MemberService memberService = new MemberServiceImpl();
        MemberDTO loginUser = memberService.loginCheck(id);

        ActionForward forward = new ActionForward();

        if (loginUser == null) {
            // 미가입자 처리
            request.setAttribute("msg", "가입되지 않은 회원입니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // 비밀번호 암호화 후 비교
        String encryptedInput = PwdChange.getPassWordToXEMD5String(pwd);
        if (!encryptedInput.equals(loginUser.getMem_pwd())) {
            request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
            return forward;
        }

        // 로그인 성공 → 세션 저장
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        // 팝업 내부 forward 처리
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/view/common/login_success.jsp");
        return forward;
    }
}
