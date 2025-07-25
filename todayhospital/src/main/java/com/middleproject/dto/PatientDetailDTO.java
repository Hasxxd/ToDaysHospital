package com.middleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDetailDTO { // 조회용
    private String patientId;
    private String patientLoginId;
    private String patientName;
    private String patientEmail;
    private String patientGender;
    private String patientAddr1;
    private String patientAddr2;
    private String patientPhone;
    private String patientZip;
    private String patientBlood;
    private String patientAllergy;
    private String patientMedication;
    private String patientDisease;
    private String patientState;
    private String patientRegno1; // 주민번호 앞자리
    private String patientRegno2; // 주민번호 뒷자리 (마스킹 또는 별도 처리 필요)
    private String patientBir;
    private String patientCreate;
    private String patientUpdate;
}
