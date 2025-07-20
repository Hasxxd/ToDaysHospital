
package com.todayhospital.dto;

import java.sql.Date; // DB DATE 타입과 매핑되는 java.sql.Date 사용
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PreMedicalFormDTO {
    // -------------------------------
    // 사전 문진 정보 (PreMedical_Form 테이블과 1:1 매핑)
    // -------------------------------
    private String bm_id; // 문진 고유 ID
    private String bm_medication; // 복용 중인 약물
    private String bm_symptom; // 증상 입력
    private String bm_travel; // 최근 여행 이력
    private Date bm_record; // 병력 또는 기록일
    private String bm_precautions; // 주의 사항 (알러지 등)
    private String mem_id; // 해당 회원 ID (회원 테이블 참조)
}
