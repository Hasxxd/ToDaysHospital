// your_script.js (또는 사용하는 JavaScript 파일 이름)

document.addEventListener('DOMContentLoaded', () => {

  // --- 기존 회원가입 폼 로직 (signup.html에서 사용될 수 있는 부분) ---
  // 이 부분은 signup.html 내에 별도의 <script> 태그로 두는 것이 더 좋지만,
  // 하나의 스크립트 파일로 관리한다면 여기에 그대로 둡니다.
  const form = document.getElementById('signup-form');
  const messageDiv = document.getElementById('message');
  const existingEmails = ['test@naver.com', 'user@naver.com'];
  const toggleButtons = document.querySelectorAll('.toggle-btn');

  // 토글 버튼 이벤트 리스너
  toggleButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      const name = btn.dataset.name;
      if (name === 'gender') {
        toggleButtons.forEach(b => {
          if (b.dataset.name === 'gender') b.classList.remove('active');
        });
        btn.classList.add('active');
      } else if (name === 'nationality') {
        toggleButtons.forEach(b => {
          if (b.dataset.name === 'nationality') b.classList.remove('active');
        });
        btn.classList.add('active');
      }
    });
  });

  // 비밀번호 보이기/숨기기 토글
  const pwToggleBtn = document.querySelector('.toggle-pw');
  const pwInput = document.getElementById('password');

  if (pwToggleBtn && pwInput) {
    pwToggleBtn.addEventListener('click', () => {
      if (pwInput.type === 'password') {
        pwInput.type = 'text';
        pwToggleBtn.textContent = '🙈'; // 아이콘 변경
      } else {
        pwInput.type = 'password';
        pwToggleBtn.textContent = '👁️'; // 아이콘 변경
      }
    });
  }

  // 폼 제출 이벤트 (이 부분은 signup.html 내의 스크립트에서 실행되는 것이 더 일반적입니다)
  if (form) { // form 요소가 존재하는 경우에만 이벤트 리스너를 추가
    form.addEventListener('submit', e => {
      e.preventDefault();
      messageDiv.textContent = '';

      if (!form.checkValidity()) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '모든 필수 항목을 올바르게 입력해주세요.';
        form.reportValidity();
        return;
      }

      const birth = form.birth.value.trim();
      if (!/^\d{8}$/.test(birth)) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '생년월일은 숫자 8자리여야 합니다.';
        form.birth.focus();
        return;
      }

      const phone = form.phone.value.trim();
      if (!/^010\d{8}$/.test(phone)) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '휴대전화번호는 01012345678 형식(숫자 11자리)이어야 합니다.';
        form.phone.focus();
        return;
      }

      const emailVal = form.email.value.trim().toLowerCase();
      if (emailVal && existingEmails.includes(emailVal)) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '이미 등록된 이메일 주소입니다.';
        form.email.focus();
        return;
      }

      const genderSelected = [...toggleButtons].some(b => b.dataset.name === 'gender' && b.classList.contains('active'));
      if (!genderSelected) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '성별을 선택해주세요.';
        return;
      }
      const nationalitySelected = [...toggleButtons].some(b => b.dataset.name === 'nationality' && b.classList.contains('active'));
      if (!nationalitySelected) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '국적을 선택해주세요.';
        return;
      }

      if (!form.agree.checked) {
        messageDiv.style.color = '#e74c3c';
        messageDiv.textContent = '약관에 동의해 주세요.';
        return;
      }

      // 📢 회원가입 성공 처리 및 팝업 띄우기
      messageDiv.style.color = '#03c75a';
      messageDiv.textContent = '가입이 성공적으로 완료되었습니다!';
      console.log('가입 정보:', {
        userid: form.userid.value.trim(),
        password: form.password.value,
        email: emailVal,
        name: form.name.value.trim(),
        birth,
        carrier: form.carrier.value,
        gender: [...toggleButtons].find(b => b.dataset.name === 'gender' && b.classList.contains('active')).dataset.value,
        nationality: [...toggleButtons].find(b => b.dataset.name === 'nationality' && b.classList.contains('active')).dataset.value,
        phone,
        agree: form.agree.checked,
      });

      // 1. 회원가입 팝업 닫기
      const signupModal = window.parent.document.getElementById('signupModal');
      if (signupModal) {
        signupModal.style.display = 'none';
      }

      // 2. 성공 메시지 팝업 띄우기 (시작화면.html의 요소를 찾아야 함)
      const successModal = window.parent.document.getElementById('successModal');
      if (successModal) {
        successModal.style.display = 'flex';
        // 3. 3초 후 메인 화면으로 리디렉션
        setTimeout(() => {
          window.parent.location.href = '시작화면.html'; // 시작화면.html의 경로로 변경하세요.
        }, 2000); // 3초 (3000 밀리초)
      } else {
        console.error("ID가 'successModal'인 요소를 찾을 수 없습니다. HTML을 확인하세요.");
      }

      form.reset(); // 폼 초기화
    });
  }


  // --- 모달 팝업 제어 JavaScript 코드 (시작화면.html에서 실행) ---
  const signupButton = document.getElementById('signupButton');
  const signupModal = document.getElementById('signupModal');
  const closeButton = document.querySelector('.close-button');

  // 회원가입 버튼 클릭 시 모달 열기
  if (signupButton) {
    signupButton.addEventListener('click', () => {
      if (signupModal) {
        signupModal.style.display = 'flex';
      }
    });
  } else {
    console.error("ID가 'signupButton'인 요소를 찾을 수 없습니다. HTML을 확인하세요.");
  }

  // 닫기 버튼 클릭 시 모달 닫기
  if (closeButton) {
    closeButton.addEventListener('click', () => {
      if (signupModal) {
        signupModal.style.display = 'none';
      }
    });
  } else {
    console.error("클래스가 'close-button'인 요소를 찾을 수 없습니다. HTML을 확인하세요.");
  }

  // 모달 외부 (배경) 클릭 시 모달 닫기
  if (signupModal) {
    window.addEventListener('click', (event) => {
      if (event.target === signupModal) {
        signupModal.style.display = 'none';
      }
    });
  }

}); // DOMContentLoaded 닫힘