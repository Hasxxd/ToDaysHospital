package com.todayhospital.dto;

import java.sql.Date; // DB DATE 타입과 매핑되는 java.sql.Date 사용
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {
    // -------------------------------
    // 회원 기본 정보 (MEMBER 테이블과 1:1 매핑)
    // -------------------------------
    private String mem_id; // 회원 ID (Primary Key)
    private String mem_login_id; // 로그인용 ID
    private String mem_pw; // 비밀번호
    private String mem_name; // 회원 이름

    // private Date mem_bir; // 생년월일 (java.sql.Date로 DB DATE 타입과 매핑)
    private String mem_bir; // 생년월일
    private String mem_regno1; // 주민등록번호 앞자리
    private String mem_regno2; // 주민등록번호 뒷자리
    private String mem_zip; // 우편번호
    private String mem_addr1; // 기본 주소
    private String mem_addr2; // 상세 주소
    private String mem_hometel; // 자택 전화번호
    private String mem_hp; // 휴대폰 번호
    private String mem_mail; // 이메일
    private String mem_job; // 직업
    private String mem_status; // 회원 상태 (enum 처리 가능 영역)

    // // -------------------------------
    // // 회원 탈퇴 관련 필드 (관리자 페이지 용도)
    // // -------------------------------
    // private int mem_state; // 회원 상태: 가입회원 1, 탈퇴회원 2
    // private String mem_delcont; // 탈퇴 사유
    // private String mem_deldate; // 탈퇴 일자 (문자열, 추후 Date로 개선 가능)

    // // -------------------------------
    // // 페이징 처리용 필드 (회원 리스트 조회 시 사용)
    // // -------------------------------
    // private int startrow; // 페이징 시작 행 번호
    // private int endrow; // 페이징 끝 행 번호

    // // -------------------------------
    // // 검색 처리용 필드 (회원 검색 기능에서 사용)
    // // -------------------------------
    // private String find_name; // 검색 키워드 (이름)
    // private String find_field; // 검색 필드 (ex. 이름, 아이디 등)
}
