
// ===== MFrontController.java =====
package com.todayhospital.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Map<String, Action> actionMap;

    @Override
    public void init() throws ServletException {
        actionMap = new HashMap<>();
        actionMap.put("/index.do", new IndexController());
        // 필요 시 여기에 다른 컨트롤러도 등록
        // actionMap.put("/login_ok.do", new LoginOKController());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length());

        Action action = actionMap.get(command);

        if (action != null) {
            try {
                ActionForward forward = action.execute(request, response);

                if (forward.isRedirect()) {
                    response.sendRedirect(forward.getPath());
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                    dispatcher.forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}