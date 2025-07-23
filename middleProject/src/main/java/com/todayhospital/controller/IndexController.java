
// ===== IndexController.java =====
package com.todayhospital.controller;

import com.todayhospital.dto.PatientDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();

        try {
            PatientDTO loginPatient = (PatientDTO) request.getSession().getAttribute("loginPatient");

            if (loginPatient != null) {
                forward.setPath("/WEB-INF/views/home/home.jsp");
            } else {
                forward.setPath("/WEB-INF/views/total_login/login.jsp");
            }

            forward.setRedirect(false); // forward 방식 유지
        } catch (Exception e) {
            e.printStackTrace();
            forward.setPath("/WEB-INF/views/error/error.jsp");
            forward.setRedirect(false);
        }

        return forward;
    }
}