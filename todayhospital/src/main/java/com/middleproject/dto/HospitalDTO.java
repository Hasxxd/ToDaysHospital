package com.middleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalDTO {
    // -------------------------------
    // 병원 기본 정보 (HOSPITAL_BASIC_INFO 테이블)
    // -------------------------------
    private String hId; // 병원 ID (PK)
    private String hBasicName; // 병원명
    private String hBasicAddr; // 주소
    private String hBasicTel; // 전화번호
}
