package com.todayhospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalDTO {
    // -------------------------------
    // 병원 기본 정보 (HOSPITAL_BASIC_INFO 테이블)
    // -------------------------------
    private String h_id; // 병원 ID (PK)
    private String h_basic_name; // 병원명
    private String h_basic_addr; // 주소
    private String h_basic_tel; // 전화번호
}
