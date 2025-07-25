function createNotice() {
  alert('공지 등록 페이지로 이동 예정입니다.');
}

function editNotice(id) {
  alert(id + '번 공지를 수정합니다.');
}

function deleteNotice(id) {
  if (confirm(id + '번 공지를 삭제하시겠습니까?')) {
    alert('삭제되었습니다.');
    // 실제 삭제 처리 로직은 AJAX 또는 서버 연동 필요
  }
}
