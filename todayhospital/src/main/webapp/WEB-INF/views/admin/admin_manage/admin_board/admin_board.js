document.addEventListener('DOMContentLoaded', () => {
  const tabs = document.querySelectorAll('.tab');
  const contents = document.querySelectorAll('.tab-content');

  tabs.forEach(tab => {
    tab.addEventListener('click', () => {
      tabs.forEach(t => t.classList.remove('active'));
      contents.forEach(c => c.classList.add('hidden'));

      tab.classList.add('active');
      const target = tab.getAttribute('data-tab');
      document.getElementById(target).classList.remove('hidden');
    });
  });

  // TODO: AJAX 로 공지, Q&A, 신고글, 통계 불러오기
});
