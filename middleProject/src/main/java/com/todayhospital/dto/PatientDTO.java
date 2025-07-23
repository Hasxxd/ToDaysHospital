package com.todayhospital.dto;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class PatientDTO {

    // -------------------------------
    // 환자 기본 정보 (PATIENT 테이블과 1:1 매핑)
    // -------------------------------
    private String patient_id; // 1. 환자ID (PK)
    private String patient_login_id; // 2. 로그인용 ID
    private String patient_pw; // 3. 비밀번호
    private String patient_name; // 4. 이름
    private String patient_gender; // 5. 성별 (M/F 등)
    private Date patient_bir; // 6. 생년월일
    private String patient_addr1; // 7. 주소 (기본)
    private String patient_phone; // 8. 전화번호
    private String patient_zip; // 9. 우편번호
    private String patient_mail; // 10. 이메일
    private String patient_disease; // 11. 주요 질병
    private String patient_regno2; // 12. 주민번호 뒷자리
    private String patient_addr2; // 13. 주소 (상세)
    private String patient_blood; // 14. 혈액형
    private String patient_regno1; // 15. 주민번호 앞자리
    private String patient_allerfy; // 16. 알레르기 (DB 오탈자와 일치)
    private String patient_medication; // 17. 복용 중인 약
    private String patient_state; // 18. 계정 상태 ('Y', 'N')
    private Date patient_create; // 19. 계정 생성일시
    private Date patient_update; // 20. 계정 수정일시

    // -------------------------------
    // 로그인 관련 필드 (추가 관리용)
    // -------------------------------
    private Integer login_fail_count; // 로그인 실패 횟수

    // -------------------------------
    // 조회 및 검색 보조 필드
    // -------------------------------
    private int startrow; // 페이징 시작 행
    private int endrow; // 페이징 끝 행
    private String find_name; // 검색어
    private String find_field; // 검색 대상 필드
}
