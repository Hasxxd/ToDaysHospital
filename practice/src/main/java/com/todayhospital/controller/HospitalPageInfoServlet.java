package com.todayhospital.controller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HospitalPageInfoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/hospital_Page_Info/hospital_Page_Info.jsp").forward(request, response);
	}
}