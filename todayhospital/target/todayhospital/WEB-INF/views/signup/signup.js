
document.addEventListener('DOMContentLoaded', () => {
  emailjs.init('_aJobt-qQXNG4QNkk'); // 메일전송API public key 

  const form = document.getElementById('signup-form');
  const messageDiv = document.getElementById('message');
  const existingEmails = ['test@naver.com', 'user@naver.com'];

  const toggleButtons = document.querySelectorAll('.toggle-btn');
  const sendAuthBtn = document.getElementById('sendAuthEmail');
  const verifyAuthBtn = document.getElementById('verifyAuthCode');
  const authCodeInput = document.getElementById('emailAuthCode');
  const emailAuthMessage = document.getElementById('emailAuthMessage');
  const signupBtn = document.getElementById('signupBtn');
  const authCodeContainer = document.getElementById('authCodeContainer');
  const devGeneratedCode = document.getElementById('devGeneratedCode'); // ✅ 개발용 인증코드 표시 영역

  let generatedCode = '';
  let emailVerified = false;

  const updateHiddenInput = (name, value) => {
    const hidden = document.querySelector(`input[name="${name}"]`);
    if (hidden) hidden.value = value;
  };

  toggleButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      const name = btn.dataset.name;
      toggleButtons.forEach(b => {
        if (b.dataset.name === name) b.classList.remove('active');
      });
      btn.classList.add('active');
      updateHiddenInput(name, btn.dataset.value);
    });
  });

  const pwToggleBtn = document.querySelector('.toggle-pw');
  const pwInput = document.getElementById('password');
  if (pwToggleBtn && pwInput) {
    pwToggleBtn.addEventListener('click', () => {
      if (pwInput.type === 'password') {
        pwInput.type = 'text';
        pwToggleBtn.textContent = '🙈';
      } else {
        pwInput.type = 'password';
        pwToggleBtn.textContent = '👁️';
      }
    });
  }

  //  인증 메일 전송 버튼
  sendAuthBtn.addEventListener('click', () => {
    const emailVal = form.email.value.trim();
    if (!emailVal || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailVal)) { //이메일 확인하는 정규표현식 수정x
      alert('유효한 이메일 주소를 입력하세요.');
      return;
    }

    //6자리 인증번호 생성
    generatedCode = Math.floor(100000 + Math.random() * 900000).toString();
    console.log('생성된 인증번호:', generatedCode);

    // 개발용 인증번호 화면 표시 빠린 검수를 위해 생성 삭제 가능
    devGeneratedCode.textContent = '발급된 인증번호 (개발용): ' + generatedCode;

    sendAuthBtn.disabled = true;
    sendAuthBtn.textContent = '전송 중...';

    //메일 전송 메세지 표시
    emailjs.send('service_dhekibe', 'template_u87rnrr', {
      to_email: emailVal,
      code: generatedCode
    })
      .then(() => {
        alert('인증번호가 이메일로 전송되었습니다.');
        authCodeContainer.style.display = 'flex';
        authCodeInput.focus();
      })
      .catch(err => {
        alert('메일 전송 실패: ' + (err.text || '알 수 없는 오류'));
      })
      .finally(() => {
        sendAuthBtn.disabled = false;
        sendAuthBtn.textContent = '인증 메일 전송';
      });
  });

  //  인증번호 확인 버튼
  verifyAuthBtn.addEventListener('click', () => {
    const enteredCode = authCodeInput.value.trim();
    if (enteredCode === generatedCode) {
      emailVerified = true;
      emailAuthMessage.textContent = '이메일 인증이 완료되었습니다.';
      emailAuthMessage.style.color = '#03c75a';
      signupBtn.disabled = false;
    } else {
      emailVerified = false;
      emailAuthMessage.textContent = '❌ 인증번호가 올바르지 않습니다.';
      emailAuthMessage.style.color = '#e74c3c';
      signupBtn.disabled = true;
    }
  });

  //  폼 제출
  form.addEventListener('submit', e => {
    e.preventDefault();
    messageDiv.textContent = '';

    if (!form.checkValidity()) {
      messageDiv.style.color = '#e74c3c';
      messageDiv.textContent = '모든 필수 항목을 올바르게 입력해주세요.';
      form.reportValidity();
      return;
    }

    if (!emailVerified) {
      messageDiv.style.color = '#e74c3c';
      messageDiv.textContent = '이메일 인증을 완료하세요.';
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
      messageDiv.textContent = '휴대전화번호는 01012345678 형식이어야 합니다.';
      form.phone.focus();
      return;
    }

    const emailVal = form.email.value.trim().toLowerCase();
    if (existingEmails.includes(emailVal)) {
      messageDiv.style.color = '#e74c3c';
      messageDiv.textContent = '이미 등록된 이메일 주소입니다.';
      form.email.focus();
      return;
    }

    if (!form.gender.value || !form.nationality.value) {
      messageDiv.style.color = '#e74c3c';
      messageDiv.textContent = '성별과 국적을 선택해주세요.';
      return;
    }

    if (!form.agree.checked) {
      messageDiv.style.color = '#e74c3c';
      messageDiv.textContent = '약관에 동의해 주세요.';
      return;
    }

    messageDiv.style.color = '#03c75a';
    messageDiv.textContent = '가입이 성공적으로 완료되었습니다!';
    console.log('가입 정보:', {
      userid: form.userid.value.trim(),
      password: form.password.value,
      email: emailVal,
      name: form.name.value.trim(),
      birth,
      gender: form.gender.value,
      nationality: form.nationality.value,
      phone,
      agree: form.agree.checked,
    });

    window.parent.postMessage('signupSuccess', '*');
    form.reset();
    signupBtn.disabled = true;
    emailVerified = false;
    emailAuthMessage.textContent = '';
    authCodeContainer.style.display = 'none';
    devGeneratedCode.textContent = '';
  });

  // 주소 찾기 (Daum API)
  $('#zipbtn').on('click', function () {
    new daum.Postcode({
      oncomplete: function (data) {
        var addr = data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress;
        var extraAddr = '';
        if (data.userSelectedType === 'R') {
          if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
            extraAddr += data.bname;
          }
          if (data.buildingName !== '' && data.apartment === 'Y') {
            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          if (extraAddr !== '') {
            extraAddr = ' (' + extraAddr + ')';
          }
        }
        $('#zipcode').val(data.zonecode);
        $('#addr1').val(addr);
        $('#extraAddress').val(extraAddr);
        $('#addr2').focus();
      }
    }).open();
  });
});
