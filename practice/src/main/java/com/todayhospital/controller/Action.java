package com.todayhospital.controller;
////주석

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
