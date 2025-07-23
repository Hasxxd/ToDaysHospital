package com.todayhospital.dao;

import java.util.List;
import java.util.Map;

import com.todayhospital.dto.PatientDTO;

/**
 * 회원 관련 DB 작업 인터페이스
 */
public interface PatientDAO {

    /** 전체 회원 목록 조회 */
    List<PatientDTO> getAllPatients();

    /** 아이디 중복 체크 */
    PatientDTO idCheck(String id);

    /** 비밀번호 찾기 (이름, 전화번호 등으로) */
    PatientDTO pwdPatient(PatientDTO m);

    /** 로그인 아이디로 정보 조회 */
    PatientDTO loginCheck(String id, String pw);

    /** 회원 정보 단건 조회 (로그인용) */
    PatientDTO findById(String patientId);
    
    PatientDTO loginCheck(Map<String, String> params);
}
