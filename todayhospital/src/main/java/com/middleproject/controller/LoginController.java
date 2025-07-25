package com.middleproject.controller;

import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;
import com.middleproject.service.PatientService;
import com.middleproject.service.PatientServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Action {

    private final PatientService service = new PatientServiceImpl();

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String loginId = request.getParameter("patientLoginId");
        String loginPw = request.getParameter("patientPw");
        if (loginId == null || loginId.trim().isEmpty() ||
                loginPw == null || loginPw.trim().isEmpty()) { // Null/공백 방어 코드 추가
            request.setAttribute("msg", "아이디 또는 비밀번호를 입력하세요.");
            return new ActionForward("/WEB-INF/views/total_login/login.jsp", false);
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPatientLoginId(loginId);
        loginDTO.setPatientPw(loginPw);

        // [1] 계정 잠금 여부 확인
        if (service.isAccountLocked(loginId)) {
            request.setAttribute("msg", "로그인 5회 이상 실패로 계정이 잠겼습니다. 관리자에게 문의하세요.");
            return new ActionForward("/WEB-INF/views/total_login/login.jsp", false);
        }

        // [2] 로그인 시도
        PatientDTO loginUser = service.login(loginDTO);

        if (loginUser != null) {
            // [3] 성공 시: 세션 저장 + 실패 횟수 초기화
            HttpSession session = request.getSession(true);
            session.setAttribute("loginUser", loginUser);
            service.resetLoginFailCount(loginId);

            return new ActionForward("/WEB-INF/views/home/home.jsp", false);

        } else {
            // [4] 실패 시: 실패 횟수 증가
            service.increaseLoginFailCount(loginId);
            request.setAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다.");

            return new ActionForward("/WEB-INF/views/total_login/login.jsp", false);
        }
    }
}
