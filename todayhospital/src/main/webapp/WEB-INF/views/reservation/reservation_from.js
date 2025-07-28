document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('reservationForm');
  
  form.addEventListener('submit', (e) => {
    const date = document.getElementById('RESERV_DATE').value;
    const timeSlot = document.getElementById('RESERV_TIME').value;

    if (!RESERV_DATE || !RESERV_TIME) {
      alert('예약일자와 시간대를 모두 선택해주세요.');
      e.preventDefault();
    }
  });
});
