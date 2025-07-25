package com.middleproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;

public interface PatientMapper {
    // DB 연결 확인용
    List<PatientDTO> findAllPatients();

    // 로그인 인증
    PatientDTO loginCheck(@Param("loginDTO") LoginDTO loginDTO);

    // 로그인 실패 횟수 증가
    void incrementLoginFailCount(@Param("patientLoginId") String patientLoginId);

    // 로그인 실패 횟수 초기화
    void resetLoginFailCount(@Param("patientLoginId") String patientLoginId);

    // 계정 잠금 여부 확인
    int isAccountLocked(@Param("patientLoginId") String patientLoginId);

    // 로그인 ID로 회원 찾기 (패스워드 없이)
    PatientDTO findByLoginId(@Param("patientLoginId") String patientLoginId);

    // [추가] 환자 PK 기반 조회
    PatientDTO findByPatientId(@Param("patientId") String patientId);

    // [추가] 이름과 연락처 기반 환자 조회 (비밀번호 찾기 등)
    PatientDTO findPatientByNameAndPhone(@Param("queryDto") PatientDTO queryDto);
}
