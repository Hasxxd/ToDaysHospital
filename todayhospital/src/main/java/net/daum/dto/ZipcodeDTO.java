package net.daum.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZipcodeDTO { //우편주소 검색 데이터 저장빈 클래스

	/* 
	 * 컬럼명과 변수명을 동일하게 한다.
	 */
	private int no;
	private String zipcode;//우편번호
	private String sido;//시도
	private String gugun;//구군
	private String dong;//동
	private String bunji;//번지
}
