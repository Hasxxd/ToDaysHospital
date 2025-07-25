package com.middleproject.service;

import com.middleproject.dto.PatientDTO;

public interface PatientService {
    PatientDTO idCheck(String id);

    PatientDTO pwdPatient(PatientDTO m);

    PatientDTO loginCheck(String id, String pw);

    // PatientDTO getPatient(String id);

    PatientDTO login(String memberId, String password);
}
