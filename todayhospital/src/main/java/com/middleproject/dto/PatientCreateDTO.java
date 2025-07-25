package com.middleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateDTO { // 가입용
    private String patientLoginId;
    private String patientPw;
    private String patientName;
    private String patientEmail;
    private String patientGender;
    private String patientPhone;
    private String patientZip;
    private String patientAddr1;
    private String patientAddr2;
    private String patientBir;
    private String patientBlood;
    private String patientAllergy;
    private String patientMedication;
    private String patientDisease;
    private String patientRegno1;
    private String patientRegno2;
}
