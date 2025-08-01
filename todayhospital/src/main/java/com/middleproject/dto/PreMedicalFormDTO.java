package com.middleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreMedicalFormDTO {
    // -------------------------------
    // 사전 문진 정보 (PREMEDICAL_FORM 테이블)
    // -------------------------------
    private String formId; // 문진표 ID (PK)
    private String patientId; // 환자 ID (FK)
    private String symptom; // 주요 증상
    private String symptomDetail; // 상세 증상
    private String pastDisease; // 과거 질병
    private String medication; // 복용 약물
    private String note; // 기타 참고사항
}
