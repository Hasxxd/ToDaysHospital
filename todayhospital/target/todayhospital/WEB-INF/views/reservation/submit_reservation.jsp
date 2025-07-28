<%@ page import="java.sql.*, javax.sql.*, javax.naming.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

request.setCharacterEncoding("UTF-8");
String RESERV_DATE = request.getParameter("RESERV_DATE");
String RESERV_TIME = request.getParameter("RESERV_TIME");
String BM_SYMPTOM = request.getParameter("BM_SYMPTOM");
String BM_TRAVEL = request.getParameter("BM_TRAVEL");
String BM_FAMILY = request.getParameter("BM_FAMILY");
String BM_PREGNANCY = request.getParameter("BM_PREGNANCY");
String BM_VACCINE = request.getParameter("BM_VACCINE");
String PATIENT_BLOOD = request.getParameter("PATIENT_BLOOD");
String PATIENT_DISEASE = request.getParameter("PATIENT_DISEASE");
String PATIENT_ALLERGY = request.getParameter("PATIENT_ALLERGY");
String PATIENT_MEDICATION = request.getParameter("PATIENT_MEDICATION");
String BM_PRECAUTIONS = request.getParameter("BM_PRECAUTIONS");
String memberId = request.getParameter("memberId");

Connection conn = null;
PreparedStatement pstmt = null;

try {
	Context init = new InitialContext();
	DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/yourDB");
	conn = ds.getConnection();

	String sql = "INSERT INTO reservations (member_id, RESERV_DATE, RESERV_TIME, BM_SYMPTOM, BM_TRAVEL, BM_FAMILY, BM_PREGNANCY, BM_VACCINE, PATIENT_BLOOD, PATIENT_DISEASE, PATIENT_ALLERGY, PATIENT_MEDICATION, BM_PRECAUTIONS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, memberId);
	pstmt.setString(2, RESERV_DATE);
	pstmt.setString(3, RESERV_TIME);
	pstmt.setString(4, BM_SYMPTOM);
	pstmt.setString(5, BM_TRAVEL);
	pstmt.setString(6, BM_FAMILY);
	pstmt.setString(7, BM_PREGNANCY);
	pstmt.setString(8, BM_VACCINE);
	pstmt.setString(9, PATIENT_BLOOD);
	pstmt.setString(10, PATIENT_DISEASE);
	pstmt.setString(11, PATIENT_ALLERGY);
	pstmt.setString(12, PATIENT_MEDICATION);
	pstmt.setString(13, BM_PRECAUTIONS);

	int result = pstmt.executeUpdate();

	if (result > 0) {
		response.sendRedirect("reservation_success.jsp");
	} else {
		out.println("<script>alert('예약에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
	}

} catch (Exception e) {
	e.printStackTrace();
	out.println("<script>alert('오류 발생: " + e.getMessage() + "'); history.back();</script>");
} finally {
	if (pstmt != null)
		pstmt.close();
	if (conn != null)
		conn.close();
}
%>
