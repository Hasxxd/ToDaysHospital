// main/시작화면.js

document.addEventListener('DOMContentLoaded', () => {
    // --- 기존 회원가입 모달 제어 (수정된 부분) ---
    const signupButton = document.getElementById('signupButton');
    const signupModal = document.getElementById('signupModal');
    const successModal = document.getElementById('successModal'); // 성공 모달 추가
    // 회원가입 모달 내부의 닫기 버튼 (시작화면.html에 직접 있다면)
    const closeSignupModalButton = signupModal ? signupModal.querySelector('.close-button') : null;

    // 회원가입 버튼 클릭 시 모달 열기
    if (signupButton && signupModal) {
        signupButton.addEventListener('click', () => {
            signupModal.style.display = 'flex'; // 중앙 정렬을 위해 'flex'로 설정
        });
    } else {
        console.error("오류: 'signupButton' 또는 'signupModal' 요소를 시작화면.html에서 찾을 수 없습니다.");
    }

    // 회원가입 모달의 닫기 버튼 클릭 시 모달 닫기
    if (closeSignupModalButton && signupModal) {
        closeSignupModalButton.addEventListener('click', () => {
            signupModal.style.display = 'none';
        });
    }

    // 모달 외부 (배경) 클릭 시 모달 닫기
    if (signupModal) {
        window.addEventListener('click', (event) => {
            if (event.target === signupModal) {
                signupModal.style.display = 'none';
            }
        });
    }

    // --- 회원가입 성공 메시지 수신 및 리다이렉션 (새로 추가된 부분) ---
    // iframe에서 메시지를 받는 리스너 (signup.js에서 postMessage를 보냄)
    window.addEventListener('message', function(event) {
        // 메시지의 출처가 안전한지 확인 (선택 사항이지만 보안상 권장)
        // if (event.origin !== "http://localhost:5500") return; // 개발 서버 주소로 변경 (예: Live Server 사용 시)

        if (event.data === 'signupSuccess') {
            signupModal.style.display = 'none'; // 회원가입 모달 닫기
            successModal.style.display = 'flex'; // 성공 모달 표시 (flex로 변경)

            // 2초 후 메인 화면으로 리다이렉트 (새로고침 또는 페이지 이동)
            setTimeout(() => {
                successModal.style.display = 'none'; // 성공 모달 숨기기
                window.location.reload(); // 메인 화면 URL로 변경
                // 또는 window.location.reload(); // 현재 페이지 새로고침
            }, 2000); // 2초 (2000ms)
        }
    });

    // --- 기존 로그인 상태 관리 함수 (로그인 팝업에서 호출될 예정) ---
    // 이 함수는 `login/login.js`에서 `window.opener.updateLoginStatus()`로 호출됩니다.
    window.updateLoginStatus = function(isLoggedIn, username = '') {
        const authButtons = document.querySelector('.auth-buttons');

        if (isLoggedIn) {
            // 로그인 상태일 때 버튼 대신 사용자 이름과 로그아웃 버튼 표시
            authButtons.innerHTML = `<span style="font-weight: bold;">${username}님 환영합니다!</span> <button onclick="logout()">로그아웃</button>`;
        } else {
            // 로그아웃 상태일 때 원래 로그인/회원가입 버튼 표시
            authButtons.innerHTML = `<button onclick="openLoginPopup()">로그인</button> <button id="signupButton">회원가입</button>`;
            
            // DOM이 변경되었으므로 회원가입 버튼에 이벤트 리스너 다시 연결
            // 중요: 새로 생성된 회원가입 버튼에 이벤트를 다시 연결해야 합니다.
            const newSignupButton = document.getElementById('signupButton');
            if (newSignupButton && signupModal) {
                newSignupButton.addEventListener('click', () => {
                    signupModal.style.display = 'flex';
                });
            }
        }
    };

    // --- 기존 로그아웃 함수 ---
    // 이 함수는 '로그아웃' 버튼 클릭 시 호출됩니다.
    window.logout = function() {
        localStorage.removeItem('loggedInUser'); // 로컬 스토리지에서 사용자 정보 삭제
        alert('로그아웃 되었습니다.');
        updateLoginStatus(false); // UI 업데이트
        // 필요하다면 페이지 새로고침: location.reload();
    };

    // --- 기존 로그인 팝업 열기 함수 ---
    // 이 함수는 `시작화면.html`의 로그인 버튼에서 `onclick`으로 직접 호출됩니다.
    window.openLoginPopup = function() {
        const width = 450;
        const height = 550;
        const left = (window.screen.width / 2) - (width / 2);
        const top = (window.screen.height / 2) - (height / 2);
        // 새 팝업 창을 엽니다. "../login/login.html" 경로가 중요합니다.
        window.open("../total_login/total_Login/login.jsp", "loginPopup", `width=${width},height=${height},left=${left},top=${top},scrollbars=no,resizable=no`);
    };

    // --- 기존 페이지 로드 시 로그인 상태 확인 ---
    // 브라우저에 저장된 로그인 정보가 있다면 불러와 UI를 업데이트합니다.
    const storedUsername = localStorage.getItem('loggedInUser');
    if (storedUsername) {
        updateLoginStatus(true, storedUsername);
    }
});

// 🔍 증상 검색 함수 추가
window.searchBySymptom = function () {
    const keyword = document.getElementById("symptomInput").value.trim();
    const loading = document.getElementById("loadingIndicator");
    const resultArea = document.getElementById("resultArea");

    if (!keyword) {
        alert("증상을 입력해주세요.");
        return;
    }

    loading.style.display = "inline";
    resultArea.innerHTML = "";

    fetch("../search/search_symptom.jsp?symptom=" + encodeURIComponent(keyword))
        .then(res => res.json())
        .then(data => {
            loading.style.display = "none";
            if (data.length === 0) {
                resultArea.innerHTML = "<p>검색된 병원이 없습니다.</p>";
                return;
            }

            const cards = data.map(h => `
                <div class="hospital-card">
                  <h3>${h.name}</h3>
                  <p><strong>진료과:</strong> ${h.department}</p>
                  <p><strong>주소:</strong> ${h.address}</p>
                  <a href="../hospital/hospital_detail.jsp?name=${encodeURIComponent(h.name)}" class="detail-btn">병원 상세 보기</a>
                </div>
            `).join("");
            resultArea.innerHTML = cards;
        })
        .catch(err => {
            console.error(err);
            loading.style.display = "none";
            resultArea.innerHTML = "<p>오류가 발생했습니다.</p>";
        });
};

function openGuidePopup() {
  const width = 700;
  const height = 600;
  const left = (window.screen.width / 2) - (width / 2);
  const top = (window.screen.height / 2.3) - (height / 2);
  
  const popup = window.open("", "guidePopup", `width=${width},height=${height},left=${left},top=${top},resizable=no,scrollbars=yes`);
  
  const guideContent = `
    <html>
    <head>
      <title>오늘의 병원 이용 가이드</title>
      <style>
        body { font-family: 'SUIT', sans-serif; padding: 20px; line-height: 1.6; background: #f9f9f9; }
        h2 { color: #007c91; margin-bottom: 20px; text-align: center; }
        ul { padding-left: 20px; }
        li { margin-bottom: 12px; }
        .tip { margin-top: 20px; font-style: italic; color: #555; text-align: center;}
        button { margin-top: 30px; padding: 8px 16px; background-color: #00bcd4; color: white; border: none; border-radius: 6px; cursor: pointer;  display: block; margin: 30px auto 0; }
        button:hover { background-color: #0097a7; }
      </style>
    </head>
    <body>
      <h2>🏥 오늘의 병원 이용 가이드</h2>
      <ul>
        <li><strong>① 회원가입 / 로그인</strong><br>회원가입 버튼을 눌러 가입하고 로그인해 보세요!</li>
        <li><strong>② 증상으로 병원 검색</strong><br>증상만 검색해도 가야할 병원을 알 수 있어요.</li>
        <li><strong>③ 내 주변 병원 찾기</strong><br>내 주변 병원의 진료시간, 주소, 병원 소개를 한눈에 확인!</li>
        <li><strong>④ 진료 예약하기</strong><br>날짜와 시간 선택 후 간단한 정보 입력으로 예약 완료!</li>
        <li><strong>⑤ 예약현황조회</strong><br>예약현황조회에서 언제든 예약을 확인하고 관리할 수 있어요.</li>
        <li><strong>⑥ 자료실</strong><br>병원 관련 기사와 관련 글들을 확인해보세요.</li>
		<li><strong>⑦ 공지사항</strong><br>'오늘의 병원'의 공지사항을 확인해보세요.</li>
		<li><strong>⑧ 커뮤니티</strong><br>궁금한 병원 정보, Q&A 등을 확인해보세요.</li>
      </ul>
      <p class="tip">📌 예약은 중복 없이 한 번에 하나만 가능하며, 병원에서 확인 연락이 올 수 있어요.</p>
      <button onclick="window.close()">닫기</button>
    </body>
    </html>
  `;

  popup.document.write(guideContent);
  popup.document.close();
}

