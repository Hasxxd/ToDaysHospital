package com.middleproject.dao;

import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;

public interface PatientDAO {

    // 로그인 시도 (ID/PW)
    PatientDTO loginCheck(LoginDTO loginDTO);

    // 로그인 실패 횟수 증가
    void increaseLoginFailCount(String patientLoginId);

    // 로그인 실패 횟수 초기화
    void resetLoginFailCount(String patientLoginId);

    // 계정 잠금 여부 확인
    boolean isAccountLocked(String patientLoginId);

    // 아이디 중복 체크
    boolean idExists(String patientLoginId);

    // 환자 상세 정보 조회
    PatientDTO findByPatientId(String patientId);
    
    // 이름, 연락처 등으로 사용자 조회 (비밀번호 찾기 등)
    PatientDTO findPatientByNameAndPhone(PatientDTO queryDto);
}
