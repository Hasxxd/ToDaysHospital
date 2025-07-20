package com.todayhospital.controller;

import com.todayhospital.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 🧭 IndexController는 메인 페이지 첫 진입 시, 로그인 여부에 따라 경로를 분기한다.
public class IndexController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 세션에서 로그인 여부 확인
        MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");

        ActionForward forward = new ActionForward();
        if (loginMember != null) {
            // 로그인 상태 → 메인 페이지로 이동
            forward.setPath("/main.jsp");
        } else {
            // 비로그인 상태 → 로그인 페이지로 이동
            forward.setPath("/login.jsp");
        }

        forward.setRedirect(false); // 내부 포워드 방식
        return forward;
    }
}
