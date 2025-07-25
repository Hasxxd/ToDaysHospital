


/**
 * 일반 로그인 폼 처리
 */
function handleLogin(event) {
  event.preventDefault();

  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value.trim();

  if (username === 'admin' && password === '1234') {
    console.log('로그인 성공!');
    if (window.opener) {
      window.close();
    } else {
      console.log("로그인 성공! (팝업이 아니므로 창을 닫지 않습니다)");
    }
  } else {
    console.error('아이디 또는 비밀번호가 틀렸습니다.');
  }
  return false;
}

/**
 * reCAPTCHA 인증 검사
 */
function verifyAndSubmit() {
  const response = grecaptcha.getResponse();
  if (response.length === 0) {
    console.error("reCAPTCHA를 확인해주세요.");
    return false;
  } else {
    console.log("reCAPTCHA 인증 성공:", response);
    return true;
  }
}

/**
 * 카카오 로그인 팝업
 */
function openKakaoLoginPage() {
  const width = 400;
  const height = 500;
  const left = window.screenX + (window.outerWidth - width) / 2;
  const top = window.screenY + (window.outerHeight - height) / 2;

  const popup = window.open(
    '../kakao_Login/kakao_Login.html',
    '카카오 로그인',
    `width=${width},height=${height},top=${top},left=${left},resizable=no`
  );

  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다.');
  }
}

/**
 * 네이버 로그인 팝업
 */
function openNaverloginPage() {
  const width = 400;
  const height = 500;
  const left = window.screenX + (window.outerWidth - width) / 2;
  const top = window.screenY + (window.outerHeight - height) / 2;

  const popup = window.open(
    '../naverLogin/naverLogin.html',
    '네이버 로그인',
    `width=${width},height=${height},top=${top},left=${left},resizable=no`
  );

  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다.');
  }
}

/**
 * Google 로그인 성공 시 콜백
 */
function handleGoogleCredentialResponse(response) {
  const jwtToken = response.credential;
  console.log("Google JWT Token:", jwtToken);

  try {
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    console.log("--- 구글 사용자 정보 ---");
    console.log("고유 ID:", payload.sub);
    console.log("이름:", payload.name);
    console.log("이메일:", payload.email);
    console.log("----------------------");

    // window.location.href = "/main.html"; 등 리다이렉션 가능
  } catch (e) {
    console.error("JWT 디코딩 중 오류 발생:", e);
  }
}

/**
 * ✅ DOM이 준비되었을 때 모든 초기화 실행
 */
document.addEventListener('DOMContentLoaded', () => {
  // 아이디/비번 찾기 링크 이벤트 등록
  const joinMember = document.getElementById('joinMember');
  const findId = document.getElementById('findId');
  const findPassword = document.getElementById('findPassword');

  if (findId) {
    findId.addEventListener('click', (e) => {
      e.preventDefault();
      console.log('아이디 찾기 기능은 준비 중입니다.');
    });
  }

  if (findPassword) {
    findPassword.addEventListener('click', (e) => {
      e.preventDefault();
      console.log('비밀번호 찾기 기능은 준비 중입니다.');
    });
  }

  // ✅ Google 로그인 초기화 및 버튼 렌더링
  if (typeof google !== 'undefined') {
    google.accounts.id.initialize({
      client_id: "298565234749-qsj5dott56kpcushrfqc6ru3r60dmdnn.apps.googleusercontent.com",
      callback: handleGoogleCredentialResponse
    });

    const googleLoginContainer = document.getElementById('google-login-container');
    if (googleLoginContainer) {
      google.accounts.id.renderButton(
        googleLoginContainer,
        {
          theme: "outline",
          size: "large",
          type: "standard",
          text: "signin_with",
          shape: "rectangular",
          logo_alignment: "left",
          width: "280"
        }
      );

      // 자동 로그인 방지 (원할 경우)
      google.accounts.id.prompt();
    }
  } else {
    console.error("Google Identity Services 라이브러리가 로드되지 않았습니다.");
  }
});

/////////////////////// Naver 로그인 //////////////////////////
  const clientId = "17ot1xmDg7U5Bx7O7e0I";   //  앱 Client ID 변경하면 작동안해요
  const redirectUri = encodeURIComponent("http://localhost:5500/naver_callback.html");  // 콜백 URL과 정확히 일치
  const state = "RANDOM_STATE";
  document.getElementById('naverLoginBtn').onclick = function() {
      const naverAuthUrl =
          "https://nid.naver.com/oauth2.0/authorize"
          + "?response_type=code"
          + "&client_id=" + clientId
          + "&redirect_uri=" + redirectUri
          + "&state=" + state;
      window.location.href = naverAuthUrl;
  };
//////////////////////// Naver 로그인 끝 //////////////////////////


//////////////////////// Kakao 로그인 //////////////////////////

Kakao.init('f89ea99cc7619ed687553a8116698691');   // Kakao SDK 초기화
console.log("Kakao SDK 초기화 여부:", Kakao.isInitialized());   

function kakaoLogin() {
  Kakao.Auth.login({
    scope: 'profile_nickname, account_email', // 동의 받을 사용자 정보 범위
    success: function(authObj) {
      console.log('로그인 성공:', authObj);
      Kakao.Auth.setAccessToken(authObj.access_token); // 액세스 토큰 설정

      // // 로그인 성공 시 사용자 정보 자동 불러오기 및 textarea에 표시
      // Kakao.API.request({
      //   url: '/v2/user/me',
      //   success: function(res) {
      //     document.getElementById('contents').value = JSON.stringify(res, null, 2);
      //     // 팝업이면 창 닫기 (이 부분은 필요에 따라 주석처리하세요)
      //     // if (window.opener) window.close(); ,입력되는 데이터 확인을 위해 주석처리 해뒀습니다. 이메일, 닉네임. 
      //   },
      //   fail: function(err) {
      //     document.getElementById('contents').value = "사용자 정보 가져오기 실패: " + JSON.stringify(err, null, 2);
      //     if (window.opener) window.close();
      //   }
      // });
    },
    fail: function(err) {
      document.getElementById('contents').value = "로그인 실패: " + JSON.stringify(err, null, 2);
      if (window.opener) window.close();
    }
  });
}

