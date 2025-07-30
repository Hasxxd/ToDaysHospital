<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
request.setCharacterEncoding("UTF-8");

String adminId = request.getParameter("adminId");
String adminPw = request.getParameter("adminPw");

// 기본 변수 선언
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

// 로그인 결과 플래그
boolean isLoginSuccess = false;

try {
    // 1. 커넥션 풀 통해 DB 연결
    Context initContext = new InitialContext();
    Context envContext = (Context) initContext.lookup("java:comp/env");
    DataSource ds = (DataSource) envContext.lookup("jdbc/myDB"); // ▶ web.xml 또는 context.xml에 등록된 리소스 이름
    conn = ds.getConnection();

    // 2. 관리자 ID와 비밀번호 확인
    String sql = "SELECT admin_name FROM admin WHERE admin_id = ? AND admin_pw = ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, adminId);
    pstmt.setString(2, adminPw); // 비밀번호 해시 처리 해야함

    rs = pstmt.executeQuery();

    if (rs.next()) {
        // 로그인 성공
        String adminName = rs.getString("admin_name");
        session.setAttribute("adminId", adminId);
        session.setAttribute("adminName", adminName);
        isLoginSuccess = true;
    }

} catch (Exception e) {
    e.printStackTrace();
} finally {
    if (rs != null) try { rs.close(); } catch (Exception e) {}
    if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
    if (conn != null) try { conn.close(); } catch (Exception e) {}
}

// 결과에 따라 페이지 이동
if (isLoginSuccess) {
    response.sendRedirect("../../?admin/admin_home.jsp"); // 관리자 홈으로 이동
} else {
%>
    <script>
        alert("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
        history.back();
    </script>
<%
}
%>
