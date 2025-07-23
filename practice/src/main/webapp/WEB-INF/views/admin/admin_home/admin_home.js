function movePage(page) {
  switch (page) {
    case 'notice':
      location.href = '../admin_manage/admin_notice.jsp';
      break;
    case 'hospital':
      location.href = '../admin_manage/admin_hospital.jsp';
      break;
    case 'user':
      location.href = '../admin_manage/admin_user.jsp';
      break;
    default:
      alert('잘못된 경로입니다.');
  }
}
