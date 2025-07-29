function createNotice() {
  document.getElementById('notice-popup').classList.remove('hidden');
}

function closeNoticePopup() {
  document.getElementById('notice-popup').classList.add('hidden');
}

function editNotice(id) {
  alert(id + '번 공지를 수정합니다.');
  // 수정 기능 구현 예정
}

function deleteNotice(id) {
  if (confirm(id + '번 공지를 삭제하시겠습니까?')) {
    alert('삭제되었습니다.');
    // 실제 삭제 처리 로직은 AJAX 또는 서버 연동 필요
  }
}

document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('notice-form');
  form.addEventListener('submit', function (e) {
    e.preventDefault();

    const title = form.title.value.trim();
    const content = form.content.value.trim();

    if (!title || !content) {
      alert('제목과 내용을 모두 입력하세요.');
      return;
    }

    // TODO: 서버로 AJAX 전송 구현 예정
    alert(`공지 등록됨\n제목: ${title}\n내용: ${content}`);
    closeNoticePopup();
    form.reset();
  });
});
