// Kakao SDK 초기화
// 여기에 본인의 카카오 앱 키를 입력해야 합니다.
Kakao.init('f89ea99cc7619ed687553a8116698691');
console.log("Kakao SDK 초기화 여부:", Kakao.isInitialized());


function kakaoLogin() {
  Kakao.Auth.login({
    scope: 'profile_nickname', // 동의 받을 사용자 정보 범위
    success: function(authObj) {
      console.log('로그인 성공:', authObj);
      Kakao.Auth.setAccessToken(authObj.access_token); // 액세스 토큰 설정

      // 로그인 성공 시 현재 팝업 창을 닫습니다.
      if (window.opener) { // 현재 창이 팝업으로 열렸는지 확인
        console.log("팝업 창: 로그인 성공, 팝업 닫기.");
        // window.opener.location.href = '../../home/home.html'; // 부모 창 리다이렉션 로직 제거
        window.close(); // 현재 팝업 창 닫기
      } else {
        console.log("일반 창: 로그인 성공. (팝업이 아니므로 창을 닫지 않습니다.)");
        // 이 경우, 사용자가 수동으로 페이지를 닫거나 다른 페이지로 이동해야 할 수 있습니다.
      }
    },
    fail: function(err) {
      console.error('로그인 실패:', err);

      // 로그인 실패 시 현재 팝업 창을 닫습니다.
      if (window.opener) {
        console.error("팝업 창: 로그인 실패, 팝업 닫기.");
        // alert('카카오 로그인에 실패했습니다. 다시 시도해주세요.'); // alert 대신 console.error 사용
        // window.opener.location.href = '../login/kakaologin.html'; // 부모 창 리다이렉션 로직 제거
        window.close(); // 팝업 창 닫기
      } else {
        console.error("일반 창: 카카오 로그인에 실패했습니다. (팝업이 아니므로 창을 닫지 않습니다.)");
        // alert('카카오 로그인에 실패했습니다. 다시 시도해주세요.'); // alert 대신 console.error 사용
        // 이 경우, 사용자가 수동으로 페이지를 닫거나 다른 페이지로 이동해야 할 수 있습니다.
      }
    }
  });
}

/**
 * 사용자 정보를 가져오는 함수 (선택 사항, 현재 로그인 흐름에 필수 아님)
 */
function getUserInfo() {
  Kakao.API.request({
    url: '/v2/user/me',
    success: function(res) {
      console.log('사용자 정보:', res);
    },
    fail: function(err) {
      console.error('사용자 정보 가져오기 실패:', err);
    }
  });
}

// 문서 로드 완료 시 kakaoLogin() 함수를 자동으로 호출합니다.
document.addEventListener('DOMContentLoaded', function() {
  kakaoLogin();
});
