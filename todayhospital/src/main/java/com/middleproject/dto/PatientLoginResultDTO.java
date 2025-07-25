package com.middleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientLoginResultDTO { // 로그인 결과
    private String patientId;
    private String patientLoginId;
    private String patientName;
    private String patientEmail;
    private String patientState;
}
