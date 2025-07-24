package com.middleproject.controller;

import com.middleproject.dao.PatientDAO;
import com.middleproject.dao.PatientDAOImpl;
import com.middleproject.dto.PatientDTO;
import com.middleproject.mybatis.config.DBService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import com.middleproject.mappers.PatientMapper;

public class LoginController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 파라미터 수신
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        System.out.println("[DEBUG] LoginController 실행됨");
        System.out.println("로그인 요청: " + id + "/" + pw);

        // DAO 호출 → 인증 결과 확인
        PatientDAO dao = new PatientDAOImpl(DBService.SqlSessionFactory());
        PatientDTO member = dao.loginCheck(id, pw);

        try (SqlSession session = DBService.SqlSessionFactory().openSession(true)) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);

            if (member != null) {
                // 로그인 성공: 로그인 실패 카운트 초기화
                mapper.resetLoginFailCount(id);
                request.getSession().setAttribute("loginUser", member);
                ActionForward forward = new ActionForward();
                forward.setPath("/WEB-INF/views/home/home.jsp");
                forward.setRedirect(false);
                return forward;

            } else {
                // 로그인 실패: 로그인 실패 카운트 증가
                mapper.incrementLoginFailCount(id);
                request.setAttribute("msg", "로그인 실패");
                ActionForward forward = new ActionForward();
                forward.setPath("/WEB-INF/views/total_login/login.jsp");
                forward.setRedirect(false);
                return forward;
            }
        } catch (Exception e) {
            throw new RuntimeException("로그인 처리 중 오류 발생", e);
        }
    }
}
