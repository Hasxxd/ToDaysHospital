function openLoginPopup() {
  const width = 400;
  const height = 500;

  const left = window.screenX + (window.outerWidth - width) / 2;
  const top = window.screenY + (window.outerHeight - height) / 2;

  const popup = window.open(
    'login.html',
    '로그인',
    `width=${width},height=${height},top=${top},left=${left},resizable=no`
  );

  if (!popup || popup.closed || typeof popup.closed === 'undefined') {
    alert('팝업 차단이 활성화되어 있습니다. 팝업 차단을 해제해주세요.');
  }
}

function handleLogin(event) {
  event.preventDefault();

  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value.trim();

  // 실제 서비스라면 서버 인증 필요
  if (username === 'admin' && password === '1234') {
    alert('로그인 성공!');
    window.close(); // 팝업 닫기
    // 필요 시 부모창에 로그인 상태 전달 기능 추가
  } else {
    alert('아이디 또는 비밀번호가 틀렸습니다.');
  }
  return false;
}
document.addEventListener('DOMContentLoaded', () => {
  const findId = document.getElementById('findId');
  const findPassword = document.getElementById('findPassword');

  findId.addEventListener('click', (e) => {
    e.preventDefault();
    alert('아이디 찾기 기능은 준비 중입니다.');
  });

  findPassword.addEventListener('click', (e) => {
    e.preventDefault();
    alert('비밀번호 찾기 기능은 준비 중입니다.');
  });
});
