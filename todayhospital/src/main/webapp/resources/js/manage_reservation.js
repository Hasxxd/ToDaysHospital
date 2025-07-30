function showDetail(reservationId) {
	
  // 예시 데이터 (실제 데이터는 AJAX로 불러와야 함)
  const data = {
    이름: "홍길동",
    예약일: "2025-08-01",
    예약시간: "오전 10:00",
    증상: "두통과 기침",
    해외여행: "예",
    가족력: "고혈압",
    임신: "아니오",
    백신접종: "예",
    혈액형: "A형",
    기존질환: "당뇨",
    알레르기: "꽃가루",
    복용약: "타이레놀",
    유의사항: "카페인 금지"
  };

  const detailHTML = `
    <p><strong>예약자:</strong> ${data.이름}</p>
    <p><strong>예약일:</strong> ${data.예약일자}</p>
    <p><strong>예약시간:</strong> ${data.예약시간}</p>
    <p><strong>증상 내용:</strong> ${data.증상내용}</p>
    <p><strong>최근 해외 여행 여부:</strong> ${data.최근해외여행여부}</p>
    <p><strong>가족력:</strong> ${data.가족력}</p>
    <p><strong>임신 여부:</strong> ${data.임신여부}</p>
    <p><strong>최근 백신 접종 여부:</strong> ${data.최근백신접종여부}</p>
    <p><strong>혈액형:</strong> ${data.환자혈액형}</p>
    <p><strong>기존 질환:</strong> ${data.환자기존질환}</p>
    <p><strong>알레르기:</strong> ${data.환자알레르기}</p>
    <p><strong>복용 중인 약:</strong> ${data.환자복용약}</p>
    <p><strong>기타 유의사항:</strong> ${data.기타유의사항}</p>
  `;

  document.getElementById('reservationDetail').innerHTML = detailHTML;
  document.getElementById('detailModal').classList.remove('hidden');
}
