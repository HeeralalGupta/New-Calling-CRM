/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
    const rowsPerPage = 10; // Set number of rows per page
    const table = document.getElementById('rural-info');
    const tbody = table.querySelector('tbody');
    const rows = tbody.getElementsByTagName('tr');
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    const paginationDiv = document.getElementById('pagination');

    function showPage(pageNumber) {
      const start = (pageNumber - 1) * rowsPerPage;
      const end = start + rowsPerPage;

      for (let i = 0; i < rows.length; i++) {
        rows[i].style.display = (i >= start && i < end) ? '' : 'none';
      }

      // Update pagination controls
      paginationDiv.innerHTML = '';

      for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('button');
        pageLink.textContent = i;
        pageLink.className = (i === pageNumber) ? 'active' : '';
        pageLink.addEventListener('click', () => showPage(i));
        paginationDiv.appendChild(pageLink);
      }
    }

    showPage(1); // Display the first page by default
  });