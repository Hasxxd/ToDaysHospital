package com.todayhospital.controller;

import com.todayhospital.dto.PatientDTO;
import com.todayhospital.service.PatientService;
import com.todayhospital.service.PatientServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ✅ LoginOKController: 로그인 처리 담당 (POST 요청)
public class LoginOKController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");

        PatientService service = new PatientServiceImpl();
        PatientDTO member = service.login(memberId, password); // 로그인 검증

        ActionForward forward = new ActionForward();

        if (member != null) {
            // 로그인 성공 → 세션 저장 후 메인으로 이동
            request.getSession().setAttribute("loginPatient", member);
            forward.setPath("/index.do");
            forward.setRedirect(true); // 새 요청 (리다이렉트)
        } else {
            // 로그인 실패 → 에러 메시지와 함께 로그인 화면으로
            request.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            forward.setPath("/login.jsp");
            forward.setRedirect(false); // 내부 포워드
        }

        return forward;
    }
}
