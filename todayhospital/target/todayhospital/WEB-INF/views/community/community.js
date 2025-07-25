const qnaListEl = document.getElementById('qnaList');
const detailPopup = document.getElementById('detailPopup');
const writePopup = document.getElementById('writePopup');

// ❗서버 JSON 대신 임시 배열 사용
let qnaData = [];

document.addEventListener('DOMContentLoaded', () => {
  fetch('server/qna-data.json') // 예: 서버에서 가져오기
    .then(res => res.json())
    .then(data => {
      qnaData = data;
      renderQnaList();
    });
});

function renderQnaList() {
  qnaListEl.innerHTML = '';
  qnaData.forEach((item, index) => {
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `
      <div class="card-title">${item.title}</div>
      <div class="card-snippet">${item.content.slice(0, 50)}...</div>
      <button onclick="openDetailPopup(${index})">답변 보기</button>
    `;
    qnaListEl.appendChild(card);
  });
}

function openDetailPopup(index) {
  const item = qnaData[index];
  document.getElementById('detailTitle').innerText = item.title;
  document.getElementById('detailContent').innerText = item.content;
  detailPopup.style.display = 'flex';
}

function openWritePopup() {
  writePopup.style.display = 'flex';
}

function closePopup(id) {
  document.getElementById(id).style.display = 'none';
}

document.getElementById('writeForm').addEventListener('submit', (e) => {
  e.preventDefault();
  const title = document.getElementById('qnaTitle').value;
  const content = document.getElementById('qnaContent').value;

  const newPost = { title, content };

  // ❗실제 서버 저장 처리 예시
  fetch('server/save-qna.php', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(newPost)
  })
  .then(res => res.json())
  .then(result => {
    if (result.success) {
      qnaData.unshift(newPost);
      renderQnaList();
      closePopup('writePopup');
      alert('질문이 등록되었습니다!');
    } else {
      alert('등록에 실패했습니다.');
    }
  });
});
