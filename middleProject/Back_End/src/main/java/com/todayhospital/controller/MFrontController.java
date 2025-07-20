package com.todayhospital.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// MFrontController는 모든 .do 요청을 받아 어떤 액션을 수행할지 결정하고
// 결과에 따라 어디로 이동할지 제어하는 중앙 관제탑(Front Controller)이다.
public class MFrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [1] 요청 URI 전체 (예: /todayhospital/login.do)
        String requestURI = request.getRequestURI();

        // [2] 컨텍스트 경로 (예: /todayhospital)
        String contextPath = request.getContextPath();

        // [3] 실제 명령어 추출 (예: /login.do)
        String command = requestURI.substring(contextPath.length());

        // [4] 이동 정보와 실행 객체 선언
        ActionForward forward = null;
        Action action = null;

        // [5] 매핑 파일 준비 (key: 요청경로, value: 실행 클래스 정보)
        Properties prop = new Properties();
        String propertiesPath = request.getServletContext()
                .getRealPath("/WEB-INF/classes/daum.properties");

        // [6] properties 파일 로딩
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Failed to load properties file.", e);
        }

        // [7] 요청 명령에 해당하는 실행 정보 확인
        String value = prop.getProperty(command);

        if (value != null && value.startsWith("execute")) {
            // [8] execute|클래스경로 형태에서 클래스 경로만 추출
            StringTokenizer st = new StringTokenizer(value, "|");
            st.nextToken(); // "execute" 생략
            String classPath = st.nextToken();

            try {
                // [9] 클래스 동적 로딩 및 인스턴스 생성
                Class<?> controllerClass = Class.forName(classPath);
                action = (Action) controllerClass.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                    | java.lang.reflect.InvocationTargetException e) {
                throw new ServletException("Failed to load controller class.", e);
            }

            try {
                // [10] 실제 비즈니스 로직 실행
                forward = action.execute(request, response);
            } catch (Exception e) {
                throw new ServletException("Failed to execute action logic.", e);
            }
        }

        // [11] ActionForward에 따라 이동 방식 결정
        if (forward != null) {
            if (forward.isRedirect()) {
                // 리다이렉트: 클라이언트에게 새로 요청하라고 응답
                response.sendRedirect(forward.getPath());
            } else {
                // 포워드: 서버 내부에서 요청을 다음 자원으로 전달
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        } else {
            // 매핑이 존재하지 않는 요청은 404 에러 처리
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No matching controller found.");
        }
    }
}
