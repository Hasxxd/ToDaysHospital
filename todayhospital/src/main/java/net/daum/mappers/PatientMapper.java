package net.daum.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.daum.dto.MemberDTO;

public interface PatientMapper {

    // 전체 사용자 조회 (테스트용)
    List<MemberDTO> findAllPatients();

    // 로그인 인증
    MemberDTO loginCheck(@Param("patientLoginId") String patientLoginId,
                         @Param("patientPw") String patientPw);

    // 로그인 실패 횟수 증가
    void incrementLoginFailCount(@Param("patientLoginId") String patientLoginId);

    // 로그인 실패 횟수 초기화
    void resetLoginFailCount(@Param("patientLoginId") String patientLoginId);

    // 계정 잠금 여부 확인
    int isAccountLocked(@Param("patientLoginId") String patientLoginId);

    // 로그인 ID로 회원 조회
    MemberDTO findByLoginId(@Param("patientLoginId") String patientLoginId);

    // 환자 PK 기반 조회
    MemberDTO findByPatientId(@Param("patientId") String patientId);

    // 이름 + 전화번호 기반 회원 조회 (비밀번호 찾기 등)
    MemberDTO findByNameAndPhone(@Param("patientName") String patientName,
                                 @Param("patientPhone") String patientPhone);

    // 비밀번호 업데이트
    void updatePassword(@Param("patientLoginId") String patientLoginId,
                        @Param("patientPw") String patientPw);
}
