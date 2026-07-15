# Boarding House Management System (BHMS)

A web-based fullstack application for managing boarding houses, designed as a course project for a team of 4 members. The interface is intentionally kept simple and intuitive for academic and practical demonstration purposes.

## 📝 Về file Docx Report và Usecase
Nhóm đã chia Usecase từ trước khi viết docs, đây là một bước đi rất chuẩn. Việc chia code theo **Module (Vertical Slice - Cắt dọc)** vẫn là phương án tối ưu nhất. Có nghĩa là, với các Usecase được giao, bạn sẽ làm toàn bộ từ Database -> Backend API -> Frontend UI cho các Usecase đó. Nhờ vậy, team sẽ không ai đụng vào code của ai.

## 🏗 Chia Module & Cách làm việc nhóm (Dành cho 4 người)

Dựa trên danh sách 23 Usecase của nhóm (đã bổ sung Auth), dự án được chia thành 4 module chính tương ứng với 4 thành viên. Nhờ vậy, team sẽ không ai đụng vào code của ai.

- **👨‍💻 Member 1 (Quan - Leader): Billing & Maintenance Module**
  - Hóa đơn, Thanh toán, Bảo trì.
  - *Usecases*: 05, 06, 07, 08, 10, 17.
  - *Pattern áp dụng*: **Strategy** (Xử lý thanh toán) & **Singleton** (Database).
- **👨‍💻 Member 2: Auth & User Management Module**
  - Đăng nhập (OAuth), Đăng ký, Đổi mật khẩu, Quản lý hồ sơ.
  - *Usecases*: 21, 22, 23, 12, 16, 18.
- **👨‍💻 Member 3: Room & Search Engine Module**
  - Quản lý phòng, Đăng ký thuê, Tìm kiếm phòng, Thông báo.
  - *Usecases*: 01, 04, 09, 11, 14, 15.
- **👨‍💻 Member 4: Contracts & Reports Module**
  - Quản lý Hợp đồng, Cấu hình hệ thống, Tiền cọc, Báo cáo thống kê.
  - *Usecases*: 02, 03, 13, 19, 20.

---

## 🔀 Git Workflow (Quy tắc quản lý mã nguồn)

Để các bạn không giẫm chân lên nhau khi code:

1. **Nhánh chính (Main branches)**:
   - `main`: Chỉ chứa code hoàn chỉnh, ổn định.
   - `develop`: Nhánh chứa code tích hợp mới nhất.

2. **Nhánh cá nhân (Feature branches)**:
   Mỗi người khi làm tính năng của mình sẽ tạo nhánh mới từ nhánh `develop`:
   - Member 1 (Cậu - Quan): `feature/billing-maintenance`
   - Member 2: `feature/auth-users`
   - Member 3: `feature/room-search`
   - Member 4: `feature/contracts-reports`
   
   *Cách tạo nhánh:* `git checkout -b feature/billing-maintenance`

3. **Commit Convention (Chuẩn đặt tên commit)**:
   - `feat: [Mô tả]` (Thêm tính năng mới)
   - `fix: [Mô tả]` (Sửa lỗi)
   - `refactor: [Mô tả]` (Cấu trúc lại code, áp dụng pattern)

---

## 📂 Quy tắc Cấu trúc Thư mục (Domain-Driven Design)

Để code không bị lộn xộn, toàn bộ dự án áp dụng kiến trúc phân chia theo Feature/Thành viên. Mỗi người sẽ code trong một thư mục mang tên mình.

**1. Backend Structure (`backend/src/`)**
Mỗi người tạo thư mục tên mình bên trong `controllers`, `models`, `routes`.
Ví dụ phần của Quan:
- `controllers/quan/billingController.js`
- `models/quan/Bill.js`
- `routes/quan/billingRoutes.js`

**2. Frontend Structure (`frontend/src/`)**
Mỗi người tạo thư mục tên mình bên trong `modules`.
Ví dụ phần của Quan:
- `frontend/src/modules/quan/quan_module.js`
*(Code HTML tạm thời gom chung ở `index.html` nhưng JS xử lý phải tách riêng biệt)*

---

## 💻 Hướng dẫn chạy Project (Local)

### 1. Khởi động Backend (Java Spring Boot)
Dự án sử dụng Java Spring Boot.
Mở IntelliJ IDEA, chọn file `BoardinghouseApplication.java` và ấn nút **Run (Tam giác màu xanh)**.
*(Hoặc có thể mở terminal gõ `mvn spring-boot:run` trong thư mục `backend`)*
*Backend chạy mặc định ở port 5000 và sử dụng H2 Database in-memory.*

### 2. Khởi động Frontend (Vite Vanilla JS)
Mở terminal 2:
```bash
cd frontend
npm install
npm run dev
```

---

## 💡 Ghi chú về Design Pattern trong Code (Dành cho phần của bạn)
- **Strategy Pattern**: Xem package `com.example.boardinghouse.service.quan`. Cho phép mở rộng nhiều phương thức thanh toán (Bank, E-Wallet) cho Usecase 06 dễ dàng.
- **MVC & Repository Pattern**: Được Spring Boot áp dụng mặc định qua các package `controller`, `model`, `repository`.
- **Cấu trúc Domain-Driven**: Code của ai nằm trong package của người đó (VD: `.quan`).
