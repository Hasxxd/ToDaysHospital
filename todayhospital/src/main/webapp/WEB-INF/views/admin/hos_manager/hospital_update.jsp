<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="yourpackage.HospitalDAO, yourpackage.HospitalDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String hospitalId = request.getParameter("hospitalId");
    String hospitalName = request.getParameter("hospitalName");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String workingHours = request.getParameter("workingHours");
    String description = request.getParameter("description");

    HospitalDTO hospital = new HospitalDTO();
    hospital.setHospitalId(hospitalId);
    hospital.setHospitalName(hospitalName);
    hospital.setAddress(address);
    hospital.setPhone(phone);
    hospital.setWorkingHours(workingHours);
    hospital.setDescription(description);

    HospitalDAO dao = new HospitalDAO();
    boolean success = dao.updateHospital(hospital);

    if(success) {
        response.sendRedirect("hospital_edit.jsp?message=수정이 완료되었습니다.");
    } else {
        out.print("<script>alert('수정 실패'); history.back();</script>");
    }
%>
