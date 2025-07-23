const clientId = "298565234749-qsj5dott56kpcushrfqc6ru3r60dmdnn.apps.googleusercontent.com";
const recaptchaSiteKey = "6Lc2OYsrAAAAALPnug9lY6T-VHCMsnWBz6_9Z0R8";
const redirectUrl = 'https://hosday.kro.kr/middleProject/view/home/home.html';

let loginPopup = null; // 팝업 창 객체를 저장할 변수

document.addEventListener('DOMContentLoaded', () => {
  const openGoogleLoginButton = document.getElementById('open-google-login');
  if (openGoogleLoginButton) {
    openGoogleLoginButton.addEventListener('click', openLoginPopup);
  }
});

// 로그인 팝업 창을 여는 함수
function openLoginPopup() {
  const width = 450;
  const height = 550;
  const left = (window.innerWidth / 2) - (width / 2);
  const top = (window.innerHeight / 2) - (height / 2);

  // 팝업 창의 내용을 동적으로 생성
  const popupContent = `
    <!DOCTYPE html>
    <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <title>Google 로그인 및 인증</title>
      <link rel="stylesheet" href="style.css">
    </head>
    <body class="popup-body">
      <div id="g_id_onload"
           data-client_id="${clientId}"
           data-callback="opener.handleCredentialResponse">
      </div>
      <div class="g_id_signin"></div>

      <div id="recaptcha-container" class="popup-recaptcha-container">
        <form id="recaptcha-form" class="popup-recaptcha-form">
          <div class="g-recaptcha"
               data-sitekey="${recaptchaSiteKey}"
               data-callback="opener.onRecaptchaSuccess"></div>
          <br/>
          <button type="submit">확인</button>
        </form>
      </div>

      <script src="https://accounts.google.com/gsi/client" async defer></script>
      <script src="https://www.google.com/recaptcha/api.js" async defer></script>
      <script>
        // 팝업 내부에서 직접 reCAPTCHA 폼 제출을 처리
        document.addEventListener('DOMContentLoaded', () => {
            const recaptchaForm = document.getElementById('recaptcha-form');
            if (recaptchaForm) {
                recaptchaForm.addEventListener('submit', (event) => {
                    event.preventDefault(); // 폼 기본 제출 방지
                    // 부모 창의 verifyAndSubmit 함수 호출
                    if (window.opener && window.opener.verifyAndSubmit) {
                        window.opener.verifyAndSubmit();
                    }
                });
            }
        });
      </script>
    </body>
    </html>
  `;

  loginPopup = window.open('about:blank', 'googleLoginPopup',
    `width=${width},height=${height},left=${left},top=${top},scrollbars=no,resizable=no`);

  if (loginPopup) {
    loginPopup.document.write(popupContent);
    loginPopup.document.close(); // 문서 쓰기 완료
  } else {
    alert("팝업 차단 기능에 의해 로그인 창이 열리지 못했습니다. 팝업 차단을 해제해주세요.");
  }
}

let jwtToken = null;

// 구글 로그인 응답 처리 (팝업에서 호출)
function handleCredentialResponse(response) {
  jwtToken = response.credential;

  // ---▼ [추가된 코드] JWT를 해석해서 사용자 정보를 확인하는 부분 ▼---
  try {
    // 1. JWT의 Payload(두 번째 부분)를 디코딩합니다.
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // 2. 디코딩된 사용자 정보를 콘솔에 출력합니다.
    console.log("--- 구글로부터 받은 사용자 정보 ---");
    console.log("고유 ID:", payload.sub);
    console.log("이름:", payload.name);
    console.log("이메일:", payload.email);
    console.log("프로필 사진 URL:", payload.picture);
    console.log("---------------------------------");
    console.log("전체 정보 객체:", payload);

  } catch (e) {
    console.error("JWT 디코딩 오류:", e);
  }
  // ---▲ [여기까지 추가] ▲---

  // 기존 reCAPTCHA 표시 로직
  if (loginPopup && loginPopup.document) {
    loginPopup.document.getElementById("recaptcha-container").style.display = "block";
  }
}

// reCAPTCHA 인증 완료 시 호출될 콜백 (옵션)
function onRecaptchaSuccess(token) {
    // reCAPTCHA 성공 시 추가적인 로직을 여기에 넣을 수 있음
    console.log("reCAPTCHA 성공: " + token);
}

// reCAPTCHA 인증 및 제출 함수 (팝업에서 호출)
function verifyAndSubmit() {
  let recaptchaResponse = null;
  if (loginPopup && loginPopup.grecaptcha) {
    recaptchaResponse = loginPopup.grecaptcha.getResponse();
  }

  if (!recaptchaResponse) {
    alert("reCAPTCHA 인증을 완료해주세요.");
    return;
  }

  if (!jwtToken) {
    alert("먼저 Google 로그인을 완료해주세요.");
    return;
  }

  console.log("JWT: " + jwtToken);
  console.log("reCAPTCHA 응답: " + recaptchaResponse);

  // 최종적으로 home.html로 이동
  window.location.href = redirectUrl;

  // 로그인 팝업 닫기
  if (loginPopup) {
    loginPopup.close();
    loginPopup = null; // 팝업 객체 참조 해제
  }
}