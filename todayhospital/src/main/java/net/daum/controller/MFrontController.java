package net.daum.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MFrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI(); // ex. /Middle_Project/index.do
        String contextPath = request.getContextPath(); // ex. /Middle_Project
        String command = requestURI.substring(contextPath.length()); // ex. /index.do

        if (command.equals("/home.do")) {
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/home/home.jsp"); // 실제 jsp 경로
            return;
        }

        ActionForward forward = null;
        Action action = null;

        // properties 파일 불러오기
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                request.getSession().getServletContext().getRealPath("daum.properties"));
        prop.load(fis);
        fis.close();

        String value = prop.getProperty(command); // 예: /index.do → execute|net.daum.controller.IndexController

        // 예외 방지: 매핑 정보가 없을 경우 기본 컨트롤러 지정
        if (value == null) {
            value = "execute|net.daum.controller.IndexController";
        }

        if (value.startsWith("execute")) {
            try {
            	StringTokenizer st = new StringTokenizer(value.split("#")[0].trim(), "|");
                st.nextToken(); // "execute"
                String url_2 = st.nextToken(); // 컨트롤러 클래스 이름

                Class<?> url = Class.forName(url_2); // 클래스 로딩
                action = (Action) url.getDeclaredConstructor().newInstance(); // 인스턴스 생성

                try {
                    forward = action.execute(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // 결과 처리
        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }
        }
    }
}
