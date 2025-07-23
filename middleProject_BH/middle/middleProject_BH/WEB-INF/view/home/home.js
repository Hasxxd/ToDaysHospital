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
                window.location.href = '시작화면.html'; // 메인 화면 URL로 변경
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
        window.open("../total_login/total_Login/login.html", "loginPopup", `width=${width},height=${height},left=${left},top=${top},scrollbars=no,resizable=no`);
    };

    // --- 기존 페이지 로드 시 로그인 상태 확인 ---
    // 브라우저에 저장된 로그인 정보가 있다면 불러와 UI를 업데이트합니다.
    const storedUsername = localStorage.getItem('loggedInUser');
    if (storedUsername) {
        updateLoginStatus(true, storedUsername);
    }
});