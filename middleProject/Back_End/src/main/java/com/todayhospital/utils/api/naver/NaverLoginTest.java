package com.todayhospital.utils.api.naver;

public class NaverLoginTest {
    public static void main(String[] args) {
        // 테스트용 state 파라미터
        String testState = "test_state_value";

        // 구현체 인스턴스화
        NaverLoginService loginService = new NaverLoginServiceImpl();

        // 3단계: 인증 URL 생성 테스트
        try {
            String authUrl = loginService.getAuthorizationUrl(testState);
            System.out.println("[NAVER 인증 URL 생성 결과]");
            System.out.println(authUrl);
        } catch (Exception e) {
            System.out.println("Authorization URL 생성 실패: " + e.getMessage());
        }
        // accessToken 요청은 redirect_uri를 통해 code값이 실제로 주어진 경우에만 유효함
        // 아래 코드는 실제 테스트 시 네이버 인증 서버에서 code를 받은 이후 사용
        /*
         * String testCode = "받은 code 값 입력";
         * String accessToken = loginService.getAccessToken(testCode, testState);
         * System.out.println("Access Token: " + accessToken);
         * 
         */
    }
}
