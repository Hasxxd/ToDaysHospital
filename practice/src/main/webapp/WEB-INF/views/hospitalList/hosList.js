document.addEventListener("DOMContentLoaded", () => {
  const listContainer = document.getElementById("hospital-list");

  hospitals.forEach(hospital => {
    const li = document.createElement("li");
    li.textContent = `${hospital.name} - ${hospital.department}`;
    li.addEventListener("click", () => {
      // 페이지 이동 시 병원 ID를 쿼리스트링으로 넘김
	  window.location.href = `../html/hosDetail.html?id=${hospital.id}`;
    });
    listContainer.appendChild(li);
  });
});
