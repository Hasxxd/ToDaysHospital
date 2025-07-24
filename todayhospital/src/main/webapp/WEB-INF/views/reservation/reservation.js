let reservations = [];

document.addEventListener('DOMContentLoaded', () => {
  // 🟨 1. URL 파라미터에서 병원 정보 받기
  const params = new URLSearchParams(window.location.search);
  const hospitalName = params.get('name') || '알 수 없음';
  const hospitalAddr = params.get('address') || '주소 없음';
  const hospitalPhone = params.get('phone') || '전화번호 없음';

  document.getElementById('hospital-name').textContent = hospitalName;
  document.getElementById('hospital-address').textContent = '주소: ' + hospitalAddr;
  document.getElementById('hospital-phone').textContent = '전화번호: ' + hospitalPhone;

  const timeSelect = document.getElementById('time');
  const timeRadios = document.querySelectorAll('input[name="time-period"]');

  // 🟩 2. 오전/오후 선택 시 시간대 업데이트
  timeRadios.forEach(radio => {
    radio.addEventListener('change', () => {
      timeSelect.disabled = false;
      timeSelect.innerHTML = ''; // 기존 옵션 초기화
      const timePeriod = radio.value;
      const options = timePeriod === '오전'
        ? ['09:00', '09:30', '10:00', '10:30', '11:00']
        : ['13:00', '13:30', '14:00', '14:30', '15:00'];

      timeSelect.innerHTML = '<option value="">-- 시간 선택 --</option>';
      options.forEach(t => {
        const opt = document.createElement('option');
        opt.value = t;
        opt.textContent = t;
        timeSelect.appendChild(opt);
      });
    });
  });

  document.getElementById('reserveBtn').addEventListener('click', () => {
    const department = document.getElementById('department').value;
    const date = document.getElementById('date').value;
    const time = document.getElementById('time').value;
    const name = document.getElementById('name').value.trim();
    const phone = document.getElementById('phone').value.trim();

    if (!department || !date || !time || !name || !phone) {
      alert('모든 항목을 입력해주세요.');
      return;
    }

    // 🟥 3. 중복 예약 체크
    const isDuplicate = reservations.some(r =>
      r.name === name &&
      r.department === department &&
      r.date === date &&
      r.time === time &&
      r.hospital === hospitalName
    );
    if (isDuplicate) {
      alert('이미 이 시간에 해당 병원에 예약이 존재합니다.');
      return;
    }

    // 🟦 4. 예약 저장
    reservations.push({ name, phone, department, date, time, hospital: hospitalName });

    // 🟦 5. 예약 완료 페이지로 이동
    const query = `?name=${encodeURIComponent(name)}&date=${date}&time=${time}&dept=${department}&hospital=${encodeURIComponent(hospitalName)}`;
    window.location.href = `reservation-success.html${query}`;
  });
});
