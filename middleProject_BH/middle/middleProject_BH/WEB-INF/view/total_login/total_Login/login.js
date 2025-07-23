// script.js

/**
 * 로그인 팝업 창을 엽니다.
 * 이 함수는 현재 페이지의 버튼에 직접 연결되어 있지 않지만,
 * 다른 페이지에서 로그인 팝업을 띄울 필요가 있을 때 사용될 수 있습니다.
 */


function openLoginPopup() {
  const width = 400; // 팝업 창의 너비
  const height = 500; // 팝업 창의 높이

  // 팝업을 화면 중앙에 위치시키기 위한 계산
  const left = window.screenX + (window.outerWidth - width) / 2;
  const top = window.screenY + (window.outerHeight - height) / 2;

  // 'login.html' 경로의 팝업 창을 엽니다.
  const popup = window.open(
    'login.html', // 팝업으로 열릴 페이지의 URL
    '로그인', // 팝업 창의 이름
    `width=${width},height=${height},top=${top},left=${left},resizable=no` // 팝업 창 옵션
  );

  // 팝업 차단 여부를 확인합니다. (최신 브라우저에서는 완벽하게 작동하지 않을 수 있습니다.)
  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다. 팝업 차단을 해제해주세요.');
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
  }
}

/**
 * 메인 로그인 폼 제출을 처리하는 함수입니다.
 * 사용자 아이디와 비밀번호를 확인하고 로그인 성공 또는 실패 메시지를 출력합니다.
 * @param {Event} event - 폼 제출 이벤트 객체
 */
function handleLogin(event) {
  event.preventDefault(); // 폼의 기본 제출 동작을 막습니다.

  // 아이디와 비밀번호 입력 필드의 값을 가져와 공백을 제거합니다.
  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value.trim();

  // 실제 서비스에서는 이 부분에서 서버로 아이디와 비밀번호를 전송하여 인증해야 합니다.
  if (username === 'admin' && password === '1234') {
    console.log('로그인 성공!');
    // 팝업 창으로 열렸을 경우 팝업을 닫습니다.
    if (window.opener) {
      window.close();
    } else {
      // 팝업이 아닌 일반 창일 경우 로그인 성공 메시지를 출력합니다. (예: 페이지 리다이렉션)
      console.log("로그인 성공! (팝업이 아니므로 창을 닫지 않습니다)");
    }
    // 필요 시 부모 창에 로그인 상태를 전달하는 기능을 추가할 수 있습니다.
  } else {
    console.error('아이디 또는 비밀번호가 틀렸습니다.');
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
  }
  return false; // 폼 제출을 중단합니다.
}

/**
 * reCAPTCHA 인증을 처리하고 폼 제출 여부를 결정하는 함수입니다.
 */
function verifyAndSubmit() {
  const response = grecaptcha.getResponse(); // reCAPTCHA 응답 토큰을 가져옵니다.
  if (response.length === 0) {
    console.error("reCAPTCHA를 확인해주세요.");
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
    return false; // reCAPTCHA 인증이 완료되지 않았으므로 제출을 막습니다.
  } else {
    console.log("reCAPTCHA 인증 성공:", response);
    // reCAPTCHA 인증 성공 후의 추가적인 제출 로직을 여기에 추가할 수 있습니다.
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
    return true; // 폼 제출을 허용합니다.
  }
}

/**
 * 카카오 로그인 팝업 창을 엽니다.
 */
function openKakaoLoginPage() {
  const width = 400; // 팝업 창의 너비
  const height = 500; // 팝업 창의 높이
  const left = window.screenX + (window.outerWidth - width) / 2; // 화면 중앙 계산
  const top = window.screenY + (window.outerHeight - height) / 2; // 화면 중앙 계산

  // 'kakaologinSuccess.html'은 카카오 로그인 성공 후 리다이렉션될 페이지입니다.
  // 이 경로는 실제 카카오 개발자 설정의 리다이렉션 URI와 일치해야 합니다.
  const popup = window.open(
    '../kakao_Login/kakao_Login.html', // 카카오 로그인 후 리다이렉션될 페이지 URL
    '카카오 로그인', // 팝업 창의 이름
    `width=${width},height=${height},top=${top},left=${left},resizable=no` // 팝업 창 옵션
  );

  // 팝업 차단 여부를 확인합니다.
  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다. 팝업 차단을 해제해주세요.');
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
  }
}

/**
 * 구글 로그인 팝업 창을 엽니다.
 */
function openGoogleLoginPage() {
  const width = 400; // 팝업 창의 너비
  const height = 500; // 팝업 창의 높이
  const left = window.screenX + (window.outerWidth - width) / 2; // 화면 중앙 계산
  const top = window.screenY + (window.outerHeight - height) / 2; // 화면 중앙 계산

  // 'googleLogin.html'은 구글 로그인 성공 후 리다이렉션될 페이지입니다.
  // 이 경로는 실제 구글 개발자 설정의 리다이렉션 URI와 일치해야 합니다.
  const popup = window.open(
    '../google_Login/google_Login.html', // 구글 로그인 후 리다이렉션될 페이지 URL
    '구글 로그인', // 팝업 창의 이름
    `width=${width},height=${height},top=${top},left=${left},resizable=no` // 팝업 창 옵션
  );

  // 팝업 차단 여부를 확인합니다.
  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다. 팝업 차단을 해제해주세요.');
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
  }
}

/**
 * 네이버 로그인 팝업 창을 엽니다.
 */
function openNaverloginPage() {
  const width = 400; // 팝업 창의 너비
  const height = 500; // 팝업 창의 높이
  const left = window.screenX + (window.outerWidth - width) / 2; // 화면 중앙 계산
  const top = window.screenY + (window.outerHeight - height) / 2; // 화면 중앙 계산

  // 'naverLogin.html'은 네이버 로그인 성공 후 리다이렉션될 페이지입니다.
  // 이 경로는 실제 네이버 개발자 설정의 리다이렉션 URI와 일치해야 합니다.
  const popup = window.open(
    '../naverLogin/naverLogin.html', // 네이버 로그인 후 리다이렉션될 페이지 URL
    '네이버 로그인', // 팝업 창의 이름
    `width=${width},height=${height},top=${top},left=${left},resizable=no` // 팝업 창 옵션
  );

  // 팝업 차단 여부를 확인합니다.
  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    console.error('팝업 차단이 활성화되어 있습니다. 팝업 차단을 해제해주세요.');
    // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
  }
}

/**
 * DOMContentLoaded 이벤트 리스너: 문서의 DOM 트리가 완전히 로드되고 파싱된 후에 실행됩니다.
 * 회원가입, 아이디 찾기, 비밀번호 찾기 링크에 클릭 이벤트를 추가합니다.
 */
document.addEventListener('DOMContentLoaded', () => {
  // 각 링크 요소들을 ID를 통해 가져옵니다.
  const joinMember = document.getElementById('joinMember');
  const findId = document.getElementById('findId');
  const findPassword = document.getElementById('findPassword');

  // 아이디 찾기 링크 클릭 이벤트
  if (findId) { // 요소가 존재하는지 확인합니다.
    findId.addEventListener('click', (e) => {
      e.preventDefault(); // 기본 링크 동작을 막습니다.
      console.log('아이디 찾기 기능은 준비 중입니다.');
      // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
    });
  }

  // 비밀번호 찾기 링크 클릭 이벤트
  if (findPassword) { // 요소가 존재하는지 확인합니다.
    findPassword.addEventListener('click', (e) => {
      e.preventDefault(); // 기본 링크 동작을 막습니다.
      console.log('비밀번호 찾기 기능은 준비 중입니다.');
      // 실제 애플리케이션에서는 사용자에게 보여지는 커스텀 모달이나 메시지를 사용해야 합니다.
    });
  }
});
