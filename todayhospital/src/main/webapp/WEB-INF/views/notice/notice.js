const ITEMS_PER_PAGE = 5;
let currentPage = 1;
let notices = [];

document.addEventListener('DOMContentLoaded', () => {
  fetch('server/notice-data.json')
    .then(res => res.json())
    .then(data => {
      notices = data.reverse(); // 최신글 먼저
      renderNotices();
      renderPagination();
    });

  document.querySelector('.close-button').addEventListener('click', closePopup);
  document.getElementById('popup').addEventListener('click', e => {
    if (e.target.id === 'popup') closePopup();
  });
});

function renderNotices() {
  const start = (currentPage - 1) * ITEMS_PER_PAGE;
  const end = start + ITEMS_PER_PAGE;
  const list = document.getElementById('noticeList');
  list.innerHTML = '';

  notices.slice(start, end).forEach(n => {
    const li = document.createElement('li');
    li.innerHTML = `[${n.category}] ${n.title} <span class="date">${n.date}</span>`;
    li.addEventListener('click', () => openPopup(n));
    list.appendChild(li);
  });
}

function renderPagination() {
  const totalPages = Math.ceil(notices.length / ITEMS_PER_PAGE);
  const pagination = document.getElementById('pagination');
  pagination.innerHTML = '';

  for (let i = 1; i <= totalPages; i++) {
    const btn = document.createElement('button');
    btn.textContent = i;
    btn.className = (i === currentPage) ? 'active' : '';
    btn.addEventListener('click', () => {
      currentPage = i;
      renderNotices();
      renderPagination();
    });
    pagination.appendChild(btn);
  }
}

function openPopup(n) {
  document.getElementById('popup-title').textContent = n.title;
  document.getElementById('popup-date').textContent = n.date;
  document.getElementById('popup-content').textContent = n.content;
  document.getElementById('popup').classList.remove('hidden');
}

function closePopup() {
  document.getElementById('popup').classList.add('hidden');
}
