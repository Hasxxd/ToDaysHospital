
// ===== LogoutController.java =====
package com.todayhospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 세션이 존재할 경우에만 invalidate() 호출 (null 예외 방어)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 캐시 방지 헤더 설정 (브라우저 뒤로가기 방지 목적)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        // index로 리다이렉트 → 로그인 여부는 index에서 분기
        ActionForward forward = new ActionForward();
        forward.setPath("/index.do");
        forward.setRedirect(true); // 클라이언트로 새 요청 유도 (권장)
        return forward;
    }
}