package net.daum.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GongjiDTO {//공지사항 데이터 저장빈 클래스

	private int gongji_no;//공지번호
	private String gongji_name;//공지 작성자
	private String gongji_title;//공지 제목
	private String gongji_pwd;//공지 비번
	private String gongji_cont; //공지내용
	private int gongji_hit; //조회수	
	private String gongji_date;// 등록날짜	

	//페이징 쪽나누기 관련변수
	private int startrow;//시작행 번호
	private int endrow;//끝행 번호

	//검색기능
	private String find_name;//검색어
	private String find_field;//검색필드	
}
