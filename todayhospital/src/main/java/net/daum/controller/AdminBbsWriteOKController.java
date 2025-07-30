package net.daum.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.AdminBbsService;
import net.daum.service.AdminBbsServiceImpl;


public class AdminBbsWriteOKController implements Action {
	
	/* 관리자 자료실 저장 컨트롤러 클래스 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();

		String admin_id=(String)session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login.do';");
			out.println("</script>");
		}else {//관리자 로그인 된 상태	

			String uploadPath = request.getServletContext().getRealPath("/upload"); //업로드 되는 실제 경로 지정
			Path uploadDir = Paths.get(uploadPath);
			Files.createDirectories(uploadDir);// 디렉터리 없으면 생성

			BbsDTO bbs=new BbsDTO();
			AdminBbsService adminBbsService = new AdminBbsServiceImpl();

			String bbs_name = request.getParameter("bbs_name");
			//System.out.println("글쓴이 : "+ bbs_name);

			bbs.setBbs_name(bbs_name); bbs.setBbs_title(request.getParameter("bbs_title"));
			bbs.setBbs_pwd(request.getParameter("bbs_pwd")); bbs.setBbs_cont(request.getParameter("bbs_cont"));

			int seq_result = adminBbsService.seqNumberNext();//다음 시퀀스 번호값을 구한다.

			bbs.setBbs_no(seq_result); //자료실 번호 저장
			bbs.setBbs_ref(seq_result);//글 그룹번호 저장

			adminBbsService.bbsInsert(bbs);//자료실 저장    

			for (Part part : request.getParts()) {
				FileDTO fileDto = new FileDTO();
				if ("bbs_file".equals(part.getName()) && part.getSize() > 0) {//첨부파일이 있는 경우

					adminBbsService.updateAttached_file(7,seq_result);//첨부파일이 있는 경우만 자료실 테이블 tbl_newbbs의 bbs_attached_file 
					//컬럼레코드를 7값으로 수정

					String originalName = Paths.get(part.getSubmittedFileName()).getFileName().toString();//첨부된 원본 파일명을 구함
					String storedName = System.currentTimeMillis() + "_" + originalName; // 중복 방지를 위한 파일명 생성
					Path filePath = uploadDir.resolve(storedName);

					// 파일 저장 => 실제 파일 업로드
					try (InputStream input = part.getInputStream()) {
						Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
					}//자바 7에서 추가된 AutoCloseable 인터페이스를 구현 상속한 자손은 try()내에서 객체를 생성하면 finally문에서 명시적으로 닫지 않아도 자동으로 닫힌다.

					System.out.println("원본 파일명 : " + originalName);
					System.out.println("중복 방지를 위한 파일명 : " + storedName);
					System.out.println("첨부된 파일 경로 : " + filePath.toString());
					System.out.println("첨부된 파일 크기: " + part.getSize());	

					fileDto.setBbs_original_name(originalName); fileDto.setBbs_stored_name(storedName);
					fileDto.setBbs_file_path(filePath.toString()); fileDto.setBbs_file_size(part.getSize());
					fileDto.setBbs_no(seq_result);//시퀀스 번호인 자료실 번호값 저장=>외래키로 설정된 컬럼 레코드값으로 저장

					// 파일 정보 DB 저장
					/*String sql = "INSERT INTO upload_files (file_id, original_name, stored_name, file_path, file_size) "
							+ "VALUES (upload_seq.NEXTVAL, ?, ?, ?, ?)";
					try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.setString(1, originalName);
						pstmt.setString(2, storedName);
						pstmt.setString(3, filePath.toString());
						pstmt.setLong(4, part.getSize());
						pstmt.executeUpdate();
					}*/

					adminBbsService.insertFile(fileDto);

					System.out.println("\n ======================================= \n");
				}			
			}

			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_bbs_list.do");
			return forward;
		}
		return null;
	}
}
