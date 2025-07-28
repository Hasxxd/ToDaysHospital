// 증상 전체 보기 팝업
  const modal = document.getElementById("symptomModal");
  const closeBtn = document.querySelector(".modal .close");
  const symptomText = document.getElementById("symptomText");

  document.querySelectorAll(".viewDetailBtn").forEach((btn) => {
    btn.addEventListener("click", () => {
      const content = btn.getAttribute("data-symptom");
      symptomText.textContent = content;
      modal.classList.remove("hidden");
    });
  });

  closeBtn.addEventListener("click", () => {
    modal.classList.add("hidden");
});