package net.daum.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.service.AdminBbsService;
import net.daum.service.AdminBbsServiceImpl;
import net.daum.service.BbsService;
import net.daum.service.BbsServiceImpl;


public class AdminBbsEditOKController implements Action {

	/* 관리자 자료실 수정완료 컨트롤러 클래스 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		AdminBbsService adminBbsService = new AdminBbsServiceImpl();

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

			int bbs_no = Integer.parseInt(request.getParameter("bbs_no"));//히든으로 전달된 자료실 번호를 정수숫자로 변경해서 저장

			int page=1;
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));
			}	

			String bbs_name = request.getParameter("bbs_name");
			//System.out.println("글쓴이 : "+ bbs_name);
			String bbs_pwd = request.getParameter("bbs_pwd");

			bbs.setBbs_name(bbs_name); bbs.setBbs_title(request.getParameter("bbs_title"));
			bbs.setBbs_cont(request.getParameter("bbs_cont"));
			bbs.setBbs_no(bbs_no);//기준이 되는 번호값 저장

			//int seq_result = bbsdao.bbsInsert(bbs);//자료실 저장후 시퀀스 번호값 반환	

			BbsDTO db_pwd=adminBbsService.getAdminBbsCont(bbs_no);//오라클로 부터 비번을 가져옴.

			if(!db_pwd.getBbs_pwd().equals(bbs_pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
			}else {//비번이 같은 경우			

				adminBbsService.editBbs(bbs);//번호를 기준으로 글쓴이,글제목,글내용만 수정
				
				// 기존 첨부파일 삭제는 'bbs_file'이 새로 수정첨부한 파일이 업로드된 경우에만 실행
				boolean hasBbsFile = request.getParts().stream()
				    .anyMatch(part -> "bbs_file".equals(part.getName()) && part.getSize() > 0);

				if (hasBbsFile) {//수정 첨부파일이 있는 경우
				    if (db_pwd.getBbs_attached_file() == 7) {//기존 첨부파일이 있는 경우만
				        List<FileDTO> fileList = adminBbsService.getFileInfo(db_pwd.getBbs_no());//번호를 기준으로 기존 첨부파일 정보를 읽어온다.

				        for (FileDTO file : fileList) {
				            File delFile = new File(uploadPath + "/" + file.getBbs_stored_name());//삭제할 파일객체 생성
				            System.out.println("삭제 시도 파일 절대 경로: " + delFile.getAbsolutePath());

				            if (delFile.exists()) {//삭제할 파일이 있다면
				                delFile.delete();//기본 첨부파일을 upload폴더로 부터 삭제
				            }//if
				        }//향상된 확장된 for

				        adminBbsService.delFileList(bbs_no); //기존 첨부된 파일정보를 tbl_newbbs_file 테이블로 부터 삭제
				    }//if
				}//if
				
				for (Part part : request.getParts()) {
					FileDTO fileDto = new FileDTO();
					
					if ("bbs_file".equals(part.getName()) && part.getSize() > 0) {//수정 첨부파일이 있는 경우	
						
						adminBbsService.updateAttached_file(7,bbs_no);
						//첨부파일이 있는 경우만 자료실 테이블 tbl_newbbs의 bbs_attached_file 컬럼레코드를 7값으로 수정

						String originalName = Paths.get(part.getSubmittedFileName()).getFileName().toString();//첨부된 원본 파일명을 구함
						String storedName = System.currentTimeMillis() + "_" + originalName; // 중복 방지를 위한 파일명 생성
						Path filePath = uploadDir.resolve(storedName);

						// 파일 저장 => 실제 파일 업로드
						try (InputStream input = part.getInputStream()) {
							Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
						}//자바 7에서 추가된 AutoCloseable 인터페이스를 구현 상속한 자손은 try()내에서 객체를 생성하면 
						//finally문에서 명시적으로 닫지 않아도 자동으로 닫힌다.

						System.out.println("원본 파일명 : " + originalName);
						System.out.println("중복 방지를 위한 파일명 : " + storedName);
						System.out.println("첨부된 파일 경로 : " + filePath.toString());
						System.out.println("첨부된 파일 크기: " + part.getSize());	

						fileDto.setBbs_original_name(originalName); fileDto.setBbs_stored_name(storedName);
						fileDto.setBbs_file_path(filePath.toString()); fileDto.setBbs_file_size(part.getSize());
						fileDto.setBbs_no(bbs_no);//시퀀스 번호인 자료실 번호값 저장=>외래키로 설정된 컬럼 레코드값으로 저장

						adminBbsService.insertFile(fileDto);

						System.out.println("\n ======================================= \n");
					}//if			
				}//향상된 확장 for			

				ActionForward forward = new ActionForward();
				forward.setPath("admin_bbs_cont.do?bbs_no="+bbs_no+"&page="+page+"&state=cont");
				return forward;
			}//if else		
		}
		return null;
	}

}
