/**
 * 로그인 팝업 창에서 로그인 폼 제출을 처리합니다.
 */
function handleLogin(event) {
  event.preventDefault();

  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value.trim();

  // 간단한 로그인 검증 (예시)
  if (username === 'admin' && password === '1234') {
    console.log('로그인 성공!');

    // 🔵 로그인 정보를 localStorage에 저장 (메인 페이지에서도 사용 가능)
    localStorage.setItem('loggedInUser', username);

    // 🔵 부모 창이 열려 있으면 로그인 상태를 전달
    if (window.opener && !window.opener.closed) {
      window.opener.updateLoginStatus(true, username);
      window.close(); // 로그인 성공 시 팝업 닫기
    } else {
      console.log("팝업이 아닌 환경에서 로그인됨");
    }
  } else {
    console.error('아이디 또는 비밀번호가 틀렸습니다.');
    // 🔵 실제 서비스에서는 사용자에게 알림 표시 (모달 등) 처리 필요
  }

  return false;
}

/**
 * reCAPTCHA 인증을 처리하고 폼 제출 여부를 결정하는 함수입니다.
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
 * 카카오 로그인 팝업 열기
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
 * 구글 로그인 팝업 열기
 */
function openGoogleLoginPage() {
  const width = 400;
  const height = 500;
  const left = window.screenX + (window.outerWidth - width) / 2;
  const top = window.screenY + (window.outerHeight - height) / 2;

  const popup = window.open(
    '../google_Login/google_Login.html',
    '구글 로그인',
    `width=${width},height=${height},top=${top},left=${left},resizable=no`
  );

  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다.');
  }
}

/**
 * 네이버 로그인 팝업 열기
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
 * 아이디/비밀번호 찾기 클릭 이벤트 등록
 */
document.addEventListener('DOMContentLoaded', () => {
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

  // 🔵 엔터로 로그인할 수 있게 로그인 폼에 이벤트 연결
  const loginForm = document.getElementById('loginForm');
  if (loginForm) {
    loginForm.addEventListener('submit', handleLogin);
  }
});
