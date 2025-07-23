package com.todayhospital.mappers;

import java.util.List;
import com.todayhospital.dto.PatientDTO;

public interface PatientMapper {

    List<PatientDTO> selectAllPatients();

    PatientDTO selectPatientById(String mem_id);

    PatientDTO loginCheck(String mem_login_id, String mem_pw);

    List<PatientDTO> findPatientsByName(String name);

    PatientDTO findById(String memberId);

    PatientDTO pwdPatient(PatientDTO m);

    PatientDTO idCheck(String id);
}