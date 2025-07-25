package com.middleproject.service;

import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;

public interface PatientService {

    // 로그인 시도
    PatientDTO login(LoginDTO loginDTO);

    // 계정 잠금 여부 확인
    boolean isAccountLocked(String loginId);

    // 로그인 실패 횟수 증가
    void increaseLoginFailCount(String loginId);

    // 로그인 실패 횟수 초기화
    void resetLoginFailCount(String loginId);
}
