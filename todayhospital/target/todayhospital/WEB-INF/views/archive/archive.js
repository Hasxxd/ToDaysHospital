fetch('data/resources.json')
  .then(res => res.json())
  .then(data => {
    renderTable(data);

    document.getElementById('searchInput').addEventListener('input', () => {
      filterAndRender(data);
    });

    document.getElementById('categoryFilter').addEventListener('change', () => {
      filterAndRender(data);
    });
  });

function filterAndRender(data) {
  const keyword = document.getElementById('searchInput').value.toLowerCase();
  const category = document.getElementById('categoryFilter').value;
  
  const filtered = data.filter(item => {
    const matchKeyword = item.title.toLowerCase().includes(keyword);
    const matchCategory = category === '전체' || item.category === category;
    return matchKeyword && matchCategory;
  });

  renderTable(filtered);
}

function renderTable(items) {
  const tbody = document.getElementById('resourceBody');
  tbody.innerHTML = '';

  items.forEach(item => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${item.category}</td>
      <td>${item.title}</td>
      <td>${item.date}</td>
      <td><a href="${item.file}" class="download-link" download>⬇️</a></td>
    `;
    tbody.appendChild(row);
  });
}
