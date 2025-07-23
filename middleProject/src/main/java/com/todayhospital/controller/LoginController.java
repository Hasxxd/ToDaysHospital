
// ===== LoginController.java =====
package com.todayhospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// LoginController는 로그인 폼을 출력하는 역할만 수행한다.
public class LoginController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        forward.setPath("/WEB-INF/views/total_login/login.jsp"); // 뷰 경로
        forward.setRedirect(false); // 내부 포워드
        return forward;
    }
}