package com.todayhospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Action 인터페이스는 모든 요청 처리 클래스들이 따라야 할 규칙(계약)을 정의한다.
public interface Action {

    // 요청을 처리하고 이동 정보를 담은 ActionForward를 반환해야 한다.
    // request: 클라이언트 요청 정보
    // response: 응답 객체
    // 예외 발생 가능성 있으므로 throws Exception
    public abstract ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    // ※ public abstract는 인터페이스에서 생략 가능 (자동 포함)
    // ※ 실행부 {}가 없으므로 직접 호출은 불가능하며, 반드시 구현체에서 오버라이딩해야 함
}
