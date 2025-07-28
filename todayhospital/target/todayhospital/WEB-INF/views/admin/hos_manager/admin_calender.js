document.addEventListener('DOMContentLoaded', function () {
  const calendarEl = document.getElementById('calendar');
  const modal = document.getElementById('reservationModal');
  const modalBody = document.getElementById('modalBody');
  const modalClose = document.getElementById('modalClose');

  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'ko',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,listWeek'
    },
    events: '/admin_calendar_events',
    eventClick: function (info) {
      // 예약 상세 정보 (서버에서 받아온 event.extendedProps 사용)
      const event = info.event;
      const props = event.extendedProps;

      modalBody.innerHTML = `
        <p><strong>예약자:</strong> ${event.title}</p>
        <p><strong>예약일:</strong> ${event.start.toISOString().slice(0,10)}</p>
        <p><strong>예약 시간:</strong> ${props.reservTime || '-'}</p>
        <p><strong>상태:</strong> ${props.status || '-'}</p>
        <p><strong>증상 내용:</strong> ${props.symptom || '-'}</p>
        <p><strong>전화번호:</strong> ${props.phone || '-'}</p>
        <p><strong>이메일:</strong> ${props.email || '-'}</p>
      `;

      modal.classList.remove('hidden');
    }
  });

  modalClose.addEventListener('click', () => {
    modal.classList.add('hidden');
  });

  calendar.render();
});
