package net.daum.dto;

import lombok.Getter;
import lombok.Setter;

@Setter //setter() 메서드 자동 제공
@Getter //getter() 메서드 자동 제공

public class AdminDTO { //되도록이면 admin테이블 컬럼명과 빈클래스 변수명을 같게 한다.

	
	private int admin_no;	
	private String admin_id;//관리자 아이디
	private String admin_pwd;//관리자 비번
	private String admin_name;//관리자 이름	
	private String admin_date;//등록날짜
	
}
