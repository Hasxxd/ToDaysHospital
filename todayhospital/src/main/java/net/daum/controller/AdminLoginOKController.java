package net.daum.controller;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.daum.dto.AdminDTO;
import net.daum.service.AdminService;
import net.daum.service.AdminServiceImpl;
import pwdconv.PwdChange;

public class AdminLoginOKController implements Action {

	/*
	 * 관리자 정보 저장과 로그인 인증 컨트롤러
	 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();//세션 객체 생성
		AdminService adminService = new AdminServiceImpl();
	
		AdminDTO ab = new AdminDTO();
		
		//request.setCharacterEncoding("UTF-8");
		String admin_id = request.getParameter("admin_id");
		String admin_pwd = request.getParameter("admin_pwd");
		
		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(admin_pwd));//관리자 비번 암호화		
		
		/*
		ab.setAdmin_no(1);
		ab.setAdmin_id(admin_id);
		ab.setAdmin_name("관리자");
		adminService.insertAdmin(ab);//관리자 정보를 저장(관리자 아이디,암호화 된 관리자 비번,관리자 이름)
		*/
		
		
        AdminDTO admin_info=adminService.adminLogin(admin_id);
		
		if(admin_info == null) {
			out.println("<script>");
			out.println("alert('관리자 정보가 없습니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
		}else {
			if(!admin_info.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.println("<script>");
				out.println("alert('관리자 비번이 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			}else{
				session.setAttribute("admin_id", admin_id);//admin_id 세션 키이름에 아이디를 저장
				//->관리자 세션 아이디
				session.setAttribute("admin_name", admin_info.getAdmin_name());//관리자 이름을 
				//세션에 저장
				
				ActionForward forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("admin_index.do");
				return forward;//관리자 로그인 인증후 관리자 메인으로 이동
			}
		}
		
		return null;
	}
}
