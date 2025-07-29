package net.daum.dto;

import lombok.Getter;
import lombok.Setter;

@Setter //setter()메서드 자동제공
@Getter //getter()메서드 자동제공
public class BbsDTO {
	
	/*
	 * tbl_newbbs 자료실 테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스
	 */

	private int bbs_no; //자료실 번호
	private String bbs_name; //글쓴이
	private String bbs_title; //글제목
	private String bbs_pwd; //비번
	private String bbs_cont; //글내용	
	private int bbs_hit; //조회수
	private int bbs_ref; //글 그룹번호 -> 원본글과 답변글을 묶어주는 글그룹번호 역할
	private int bbs_step; //첫번째 답변글 1,두번째 답변글 2,즉 원본글과 답변글을 구분하는 번호값-> 원본글이면 0 이고 0이 아니면 답변글,몇번째 답변글인가를 알려줌
	private int bbs_level; //답변글 정렬순서
	private String bbs_date; //글등록날짜
	private int bbs_attached_file; //7이면 첨부파일이 있는 경우, null이면 첨부파일이 없는 경우
	          
	//페이징 쪽나누기 관련변수
	private int startrow;//시작행 번호
	private int endrow;//끝행 번호

	//검색기능
	private String find_name;//검색어
	private String find_field;//검색필드
}



