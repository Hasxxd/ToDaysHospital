document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('signup-form');
  const messageDiv = document.getElementById('message');
  const existingEmails = ['test@naver.com', 'user@naver.com'];
  const toggleButtons = document.querySelectorAll('.toggle-btn');

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
  });
});

$(function() {
  $('#zipbtn').on('click', function(){
    new daum.Postcode({
      oncomplete: function(data) {
        var addr = data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress;
        var extraAddr = '';
        if(data.userSelectedType === 'R'){
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            extraAddr += data.bname;
          }
          if(data.buildingName !== '' && data.apartment === 'Y'){
            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          if(extraAddr !== ''){
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
