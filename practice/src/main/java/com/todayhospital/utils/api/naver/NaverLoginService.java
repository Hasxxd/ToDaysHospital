package com.todayhospital.utils.api.naver;

public interface NaverLoginService {

    // 1. 로그인 URL 생성
    String getAuthorizationUrl(String state);

    // 2. 액세스 토큰 요청
    String getAccessToken(String code, String state);

    // 3. 사용자 정보 조회
    String getUserProfile(String accessToken);
}
