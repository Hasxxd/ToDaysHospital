// admin_hospital.js

function addHospital() {
  alert('병원 등록 기능 준비 중입니다.');
}

function editHospital(id) {
  alert(id + '번 병원 정보를 수정합니다.');
}

function deleteHospital(id) {
  if (confirm('정말 삭제하시겠습니까?')) {
    alert(id + '번 병원이 삭제되었습니다.');
  }
}
