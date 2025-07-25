package com.middleproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.middleproject.dto.PatientDTO;

public interface PatientMapper {

    List<PatientDTO> selectAllPatients();

    PatientDTO selectPatientById(String mem_id);

    List<PatientDTO> findPatientsByName(String name);

    PatientDTO findById(String memberId);

    PatientDTO pwdPatient(PatientDTO m);

    PatientDTO idCheck(String id);

    void incrementLoginFailCount(String id);
    void resetLoginFailCount(String id);

    PatientDTO loginCheck(@Param("id") String id, @Param("pw") String pw);
}