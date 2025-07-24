function deleteUser(id) {
  if (confirm('정말 이 회원을 삭제하시겠습니까?')) {
    // 실제 서버 연동 시 여기에 AJAX 호출을 넣어야 함
    alert(id + '번 회원이 삭제 처리되었습니다.');
    // 예시로 해당 행을 삭제 처리해보겠습니다
    // 삭제 버튼을 누른 요소로부터 tr을 찾아서 삭제
    const btn = event.target;
    const row = btn.closest('tr');
    if (row) {
      row.remove();
    }
  }
}
