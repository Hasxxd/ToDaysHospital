document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('adminLoginForm');
  const errorMessage = document.getElementById('errorMessage');

  form.addEventListener('submit', function (e) {
    const id = document.getElementById('adminId').value.trim();
    const pw = document.getElementById('adminPw').value.trim();

    if (!id || !pw) {
      e.preventDefault();
      errorMessage.textContent = '아이디와 비밀번호를 모두 입력해주세요.';
    }
  });
});
