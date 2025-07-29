package net.daum.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.BbsService;
import net.daum.service.BbsServiceImpl;

public class BbsWriteOKController implements Action {
	
	/* 한개 첨부파일 또는 다중 첨부파일 업로드 저장해주는 컨트롤러  -> 사용자 자료실 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String uploadPath = request.getServletContext().getRealPath("/upload"); //업로드 되는 서버 경로 지정
		Path uploadDir = Paths.get(uploadPath);
		Files.createDirectories(uploadDir);// 디렉터리 없으면 생성
		
		BbsDTO bbs=new BbsDTO();
	    BbsService bbsService = new BbsServiceImpl();//업캐스팅
	    
	    String bbs_name = request.getParameter("bbs_name");
	    //System.out.println("글쓴이 : "+ bbs_name);
	    
	    bbs.setBbs_name(bbs_name); bbs.setBbs_title(request.getParameter("bbs_title"));
	    bbs.setBbs_pwd(request.getParameter("bbs_pwd")); bbs.setBbs_cont(request.getParameter("bbs_cont"));
	    
	    int seq_result = bbsService.seqNumberNext();//다음 시퀀스 번호값을 구한다.
	    		
	    bbs.setBbs_no(seq_result); //자료실 번호 저장
	    bbs.setBbs_ref(seq_result);//글 그룹번호 저장
	    
	    bbsService.bbsInsert(bbs);//자료실 저장    
        
		for (Part part : request.getParts()) {//다중 첨부 파일이 있는것 만큼 반복
			FileDTO fileDto = new FileDTO();
			if ("bbs_file".equals(part.getName()) && part.getSize() > 0) {//첨부파일이 있는 경우
				
				bbsService.updateAttached_file(7,seq_result);//첨부파일이 있는 경우만 자료실 테이블 tbl_newbbs의 bbs_attached_file 
				//컬럼레코드를 7값으로 수정
				
				String originalName = Paths.get(part.getSubmittedFileName()).getFileName().toString();//첨부된 원본 파일명을 구함
				String storedName = System.currentTimeMillis() + "_" + originalName; // 중복 방지를 위한 파일명 생성
				/* System.currentTimeMillis()는 자바(Java)에서 현재 시간을 **밀리초 단위(long 타입)**로 반환하는 메서드 */
				
				Path filePath = uploadDir.resolve(storedName);

				// 파일 저장 => 실제 파일 업로드
				try (InputStream input = part.getInputStream()) {
					Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
					/* StandardCopyOption.REPLACE_EXISTING	대상 파일이 이미 존재하면 덮어쓴다. */
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
			    
			    bbsService.insertFile(fileDto);//첨부파일 정보 저장
				
				System.out.println("\n ======================================= \n");
			}			
		}

		out.println("<script>alert('파일 업로드 or DB 저장 완료');location.href='bbs_list.do';</script>");
		
		return null;
	}

}
