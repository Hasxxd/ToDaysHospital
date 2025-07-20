package com.todayhospital.controller;

// ActionForward는 이동 경로(path)와 이동 방식(redirect/forward)을 담는 전용 상자 역할을 한다.
public class ActionForward {

    // 이동 방식: true면 리다이렉트, false면 포워드
    private boolean isRedirect;

    // 이동할 주소: JSP 경로 또는 서블릿 매핑
    private String path;

    // 이동 방식 반환 (getter)
    public boolean isRedirect() {
        return isRedirect;
    }

    // 이동 방식 설정 (setter)
    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }

    // 이동 경로 반환 (getter)
    public String getPath() {
        return path;
    }

    // 이동 경로 설정 (setter)
    public void setPath(String path) {
        this.path = path;
    }
}
