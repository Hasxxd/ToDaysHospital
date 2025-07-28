package com.middleproject.controller;

import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;
import com.middleproject.service.PatientService;
import com.middleproject.service.PatientServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// LoginOKController: 로그인 처리 담당 (POST 요청)
public class LoginOKController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String memberId = request.getParameter("patientLoginId");
        String password = request.getParameter("patientPw");

        PatientService service = new PatientServiceImpl();

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPatientLoginId(memberId);
        loginDTO.setPatientPw(password);

        PatientDTO member = service.login(loginDTO);
        System.out.println("[DEBUG] ID: " + memberId + ", PW: " + password);
        ActionForward forward = new ActionForward("/WEB-INF/views/total_login/login.jsp", false);

        if (member != null) {
            // 로그인 성공 → 세션 저장 후 메인으로 이동
            request.getSession().setAttribute("loginPatient", member);
            forward.setPath("/index.do");
            forward.setRedirect(true); // 새 요청 (리다이렉트)
            System.out.println("[INFO] 로그인 성공 - " + member.getPatientName());
        } else {
            // 로그인 실패 → 에러 메시지와 함께 로그인 화면으로
            request.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            forward.setPath("/WEB-INF/views/total_login/login.jsp");
            forward.setRedirect(false); // 내부 포워드
        }

        return forward;
    }
}