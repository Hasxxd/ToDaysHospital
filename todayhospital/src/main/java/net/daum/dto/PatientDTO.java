package net.daum.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

    // -------------------------------
    // 환자 기본 정보 (PATIENT 테이블과 1:1 매핑)
    // -------------------------------
    private String patientId; // 1. 환자ID (PK)
    private String patientLoginId; // 2. 로그인용 ID
    private String patientPw; // 3. 비밀번호
    private String patientName; // 4. 이름
    private String patientGender; // 5. 성별 (M/F 등)
    private Date patientBir; // 6. 생년월일
    private String patientAddr1; // 7. 주소 (기본)
    private String patientPhone; // 8. 전화번호
    private String patientZip; // 9. 우편번호
    private String patientEMail; // 10. 이메일
    private String patientDisease; // 11. 주요 질병
    private String patientRegno2; // 12. 주민번호 뒷자리
    private String patientAddr2; // 13. 주소 (상세)
    private String patientBlood; // 14. 혈액형
    private String patientRegno1; // 15. 주민번호 앞자리
    private String patientAllerfy; // 16. 알레르기 (DB 오탈자와 일치)
    private String patientMedication; // 17. 복용 중인 약
    private String patientState; // 18. 계정 상태 ('Y', 'N')
    private Date patientCreate; // 19. 계정 생성일시
    private Date patientUpdate; // 20. 계정 수정일시

    // -------------------------------
    // 로그인 관련 필드 (추가 관리용)
    // -------------------------------
    private int loginFailCount; // 로그인 실패 횟수

    // -------------------------------
    // 조회 및 검색 보조 필드
    // -------------------------------
    private int startrow; // 페이징 시작 행
    private int endrow; // 페이징 끝 행
    private String findName; // 검색어
    private String findField; // 검색 대상 필드
}
