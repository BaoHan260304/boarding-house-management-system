import '../../style.css';

const API_BILLS = 'http://localhost:5000/api/bills';
const API_MAINT = 'http://localhost:5000/api/maintenance';

document.addEventListener('DOMContentLoaded', () => {
  const billForm = document.getElementById('create-bill-form');
  const maintForm = document.getElementById('create-maintenance-form');
  const billsContainer = document.getElementById('bills-container');
  const maintContainer = document.getElementById('maintenance-container');

  // --- BILLS LOGIC (UC 05, 06, 08) ---
  const fetchBills = async () => {
    try {
      const res = await fetch(API_BILLS);
      const bills = await res.json();
      renderBills(bills);
    } catch (error) { console.error(error); }
  };

  const renderBills = (bills) => {
    billsContainer.innerHTML = bills.map(b => `
      <div class="room-card">
        <h3>Room ${b.roomId} - ${b.month}</h3>
        <p>⚡ Electricity: $${b.electricity}</p>
        <p>💧 Water: $${b.water}</p>
        <p>🏠 Rent: $${b.roomRent}</p>
        <h4 style="margin: 0.5rem 0;">Total: $${b.totalAmount}</h4>
        <p>Status: <span class="badge ${b.status === 'Paid' ? 'badge-available' : 'badge-maintenance'}">${b.status}</span></p>
        ${b.status === 'Unpaid' ? `
          <div class="actions" style="margin-top: 1rem; flex-direction: column;">
            <button onclick="window.payBill(${b.id}, 'Bank')" class="btn btn-sm btn-primary">Pay via Bank Transfer</button>
            <button onclick="window.payBill(${b.id}, 'E-Wallet')" class="btn btn-sm btn-success">Pay via E-Wallet</button>
          </div>
        ` : ''}
      </div>
    `).join('');
  };

  window.payBill = async (id, method) => {
    // Mock user input for Bank/E-Wallet details
    const payload = method === 'Bank' 
      ? { method: method, bankName: 'Vietcombank', accountNumber: '123456789' }
      : { method: method, walletProvider: 'Momo', phone: '0987654321' };

    try {
      const res = await fetch(`${API_BILLS}/${id}/pay`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
      const data = await res.json();
      alert(data.message || data.error);
      if (res.ok) fetchBills();
    } catch (error) { console.error(error); }
  };

  billForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
      roomId: document.getElementById('bill-roomId').value,
      month: document.getElementById('bill-month').value,
      electricity: document.getElementById('bill-electricity').value,
      water: document.getElementById('bill-water').value,
      roomRent: document.getElementById('bill-rent').value
    };
    try {
      const res = await fetch(API_BILLS, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
      if (res.ok) { billForm.reset(); fetchBills(); }
    } catch (error) { console.error(error); }
  });

  // --- MAINTENANCE LOGIC (UC 07) ---
  const fetchMaintenance = async () => {
    try {
      const res = await fetch(API_MAINT);
      const reqs = await res.json();
      maintContainer.innerHTML = reqs.map(r => `
        <div style="border-bottom: 1px solid var(--border); padding: 0.5rem 0; display: flex; justify-content: space-between; align-items: center;">
          <div>
            <strong>Room ${r.roomId}:</strong> ${r.issueDescription} 
            <span class="badge badge-warning" style="margin-left: 1rem;">${r.status}</span>
          </div>
          ${r.status !== 'Resolved' ? `
            <div>
              <button onclick="window.updateMaint(${r.id}, 'In Progress')" class="btn btn-sm btn-primary">Mark In Progress</button>
              <button onclick="window.updateMaint(${r.id}, 'Resolved')" class="btn btn-sm btn-success">Mark Resolved</button>
            </div>
          ` : ''}
        </div>
      `).join('');
    } catch (error) { console.error(error); }
  };

  window.updateMaint = async (id, status) => {
    try {
      const res = await fetch(`${API_MAINT}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status })
      });
      const data = await res.json();
      alert(data.message || data.error);
      if (res.ok) fetchMaintenance();
    } catch (error) { console.error(error); }
  };

  maintForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
      roomId: document.getElementById('maint-roomId').value,
      issueDescription: document.getElementById('maint-desc').value
    };
    try {
      const res = await fetch(API_MAINT, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
      if (res.ok) { maintForm.reset(); fetchMaintenance(); alert('Maintenance requested submitted!'); }
    } catch (error) { console.error(error); }
  });

  // Init fetch
  fetchBills();
  fetchMaintenance();
});
