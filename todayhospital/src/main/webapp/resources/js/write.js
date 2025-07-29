document.getElementById('writeForm').addEventListener('submit', function (e) {
  e.preventDefault();
  const title = document.getElementById('title').value;
  const content = document.getElementById('content').value;
  const category = document.getElementById('category').value;
  const date = new Date().toISOString().split('T')[0];

  const newNotice = { title, content, date, category };

  alert("🚀 등록 완료! (서버 연결 시 저장)");

  // 실제 서버엔 fetch POST 등으로 전달
  // 예: fetch('/api/notice', { method: 'POST', body: JSON.stringify(newNotice) })

  // 테스트용으로 콘솔 출력
  console.log('등록된 공지사항:', newNotice);
  window.location.href = "notice.html";
});
