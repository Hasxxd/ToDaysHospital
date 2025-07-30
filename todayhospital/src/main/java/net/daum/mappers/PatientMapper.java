package net.daum.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import net.daum.dto.MemberDTO;

public interface PatientMapper {

    // 전체 회원 목록 조회
    List<MemberDTO> findAllPatients();

    // 로그인 인증 (ID + PW 직접 전달)
    MemberDTO loginCheck(@Param("patientLoginId") String patientLoginId,
                         @Param("patientPw") String patientPw);

    // 로그인 실패 횟수 증가
    void incrementLoginFailCount(@Param("patientLoginId") String patientLoginId);

    // 로그인 실패 횟수 초기화
    void resetLoginFailCount(@Param("patientLoginId") String patientLoginId);

    // 계정 잠금 여부 확인
    int isAccountLocked(@Param("patientLoginId") String patientLoginId);

    // 로그인 ID로 사용자 조회
    MemberDTO findByLoginId(@Param("patientLoginId") String patientLoginId);

    // 환자 PK로 조회
    MemberDTO findByPatientId(@Param("patientId") String patientId);

    // 이름 + 연락처 기반 사용자 조회
    MemberDTO findPatientByNameAndPhone(@Param("queryDto") MemberDTO queryDto);
}