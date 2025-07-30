document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const id = parseInt(params.get("id"));

  const hospital = hospitals.find(h => h.id === id);
  const detailDiv = document.getElementById("hospital-detail");

  if (!hospital) {
    detailDiv.innerHTML = "<p>병원 정보를 찾을 수 없습니다.</p>";
    return;
  }

  detailDiv.innerHTML = `
    <h2>${hospital.name}</h2>
    <p>주소: ${hospital.address}</p>
    <p>진료과: ${hospital.department}</p>
    <p>전화번호: ${hospital.phone}</p>

    <h3>예약하기</h3>
    <form id="reserve-form">
      <label>예약 날짜:
        <input type="date" required />
      </label>
      <label>예약 시간:
        <select required>
          <option>09:00</option>
          <option>10:00</option>
          <option>11:00</option>
          <option>14:00</option>
          <option>15:00</option>
        </select>
      </label>
      <button type="submit">예약</button>
    </form>
  `;

  document.getElementById("reserve-form").addEventListener("submit", (e) => {
    e.preventDefault();
    alert("예약이 완료되었습니다!");
  });
});
