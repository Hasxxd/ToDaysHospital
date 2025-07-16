package com.todayhospital.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {
	private String mem_id;
	private String mem_pwd;
	private String mem_name;
	private String mem_zip;
	private String mem_zip2;
	private String mem_addr;
	private String mem_addr2;
	private String mem_phone01;
	private String mem_phone02;
	private String mem_phone03;
	private String mail_id;
	private String mail_domain;
	private String mem_date;
	private int mem_state;// 가입회원이면 1,탈퇴 회원이면 2
	private String mem_delcont;// 탈퇴사유
	private String mem_deldate;// 탈퇴 날짜

	// 페이징 쪽나누기 관련변수 -> 관리자 회원관리에서 필요
	private int startrow;// 시작행 번호
	private int endrow;// 끝행 번호

	// 검색기능 -> 관리자 회원관리에서 필요
	private String find_name;// 검색어
	private String find_field;// 검색필드
}