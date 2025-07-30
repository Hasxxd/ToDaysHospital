// member_home.js - 로그인된 회원 전용 페이지 전용 JS

document.addEventListener('DOMContentLoaded', () => {
    // --- 증상 검색 ---
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

    // --- 이용 가이드 팝업 ---
    window.openGuidePopup = function () {
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
                    button { margin-top: 30px; padding: 8px 16px; background-color: #00bcd4; color: white; border: none; border-radius: 6px; cursor: pointer; display: block; margin: 30px auto 0; }
                    button:hover { background-color: #0097a7; }
                </style>
            </head>
            <body>
                <h2>🏥 오늘의 병원 이용 가이드</h2>
                <ul>
                    <li><strong>① 증상으로 병원 검색</strong><br>증상만 입력해도 가까운 병원을 찾을 수 있어요.</li>
                    <li><strong>② 예약 확인</strong><br>내 예약 현황을 바로 조회할 수 있어요.</li>
                    <li><strong>③ 진료 내역 확인</strong><br>이전 진료기록을 다시 볼 수 있어요.</li>
                    <li><strong>④ 정보 수정</strong><br>연락처나 비밀번호를 쉽게 바꿀 수 있어요.</li>
                    <li><strong>⑤ 자료실/공지/커뮤니티</strong><br>다양한 의료정보와 사용자 커뮤니티에 참여하세요.</li>
                </ul>
                <p class="tip">📌 예약은 1건만 가능하며, 병원에서 확인 연락이 갈 수 있어요.</p>
                <button onclick="window.close()">닫기</button>
            </body>
            </html>
        `;

        popup.document.write(guideContent);
        popup.document.close();
    };
});
