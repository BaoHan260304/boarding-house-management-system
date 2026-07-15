# Phân tích Docs & Đề xuất bổ sung

Chào Leader, sau khi rà soát lại bộ 20 Usecase hiện tại của nhóm, mình nhận thấy hệ thống đang thiếu đi một mảng cực kỳ quan trọng đối với bất kỳ ứng dụng web nào: **Authentication (Xác thực người dùng)**. 

Bạn đã nhận ra đúng vấn đề rồi đấy, chúng ta đang thiếu OAuth và các tính năng đăng nhập. Để hệ thống hoàn chỉnh, bạn nên bổ sung thêm 3 Usecase sau vào danh sách (đánh số 21, 22, 23):

### Các Usecase Cần Bổ Sung (Nên chèn vào docs)
**UC 21: Authenticate User (Login / OAuth)**
- **Actor:** Tenant, Staff, Landlord.
- **Description:** Cho phép người dùng đăng nhập vào hệ thống bằng tài khoản nội bộ (Email/Password) hoặc thông qua cổng OAuth 2.0 của bên thứ ba (đăng nhập nhanh bằng Google/Facebook). Hệ thống sẽ cấp token và phân quyền tương ứng.

**UC 22: Register Account**
- **Actor:** Tenant.
- **Description:** Cho phép khách hàng mới (Tenant) tạo tài khoản trên hệ thống để bắt đầu tìm kiếm phòng và thực hiện đăng ký thuê. (Staff và Landlord sẽ được cấp tài khoản bởi hệ thống, không tự đăng ký).

**UC 23: Reset Password**
- **Actor:** All Users.
- **Description:** Hỗ trợ người dùng khôi phục lại mật khẩu khi bị quên thông qua mã OTP gửi qua Email hoặc SMS.

---

## 🏗 Chia lại công việc cho 4 Thành Viên (Đều và Hợp Lý)

Với 23 Usecase (20 cũ + 3 mới), mình chia dự án thành 4 Module chuẩn theo nguyên tắc **Vertical Slice**. Mỗi người sẽ ôm trọn một mảng từ UI đến Database, số lượng Usecase dao động từ 5-6 UCs/người:

### 👨‍💻 Module 1 (Quan - Leader): Billing & Maintenance (6 UCs)
*Vì bạn là Leader, ôm mảng Thanh toán & Bảo trì là quan trọng và thể hiện nhiều logic nhất.*
- **UC 05:** View Utility Bills
- **UC 06:** Pay Rent Online
- **UC 08:** Input Billing Information
- **UC 10:** Process Payment Transaction (Xử lý Webhook từ Ngân hàng)
- **UC 07:** Submit Maintenance Requests
- **UC 17:** Update Maintenance Status (Staff cập nhật trạng thái sửa chữa)

### 👨‍💻 Module 2 (Member 2): Auth & User Management (6 UCs)
- **UC 21:** Authenticate User (Login / OAuth Google) **[Mới]**
- **UC 22:** Register Account **[Mới]**
- **UC 23:** Reset Password **[Mới]**
- **UC 18:** Manage Personal Profile
- **UC 12:** Manage Tenant Profiles
- **UC 16:** Manage Staff Accounts

### 👨‍💻 Module 3 (Member 3): Room & Search Engine (6 UCs)
- **UC 01:** Manage Room Information
- **UC 09:** Manage Room Status
- **UC 11:** Search Available Rooms
- **UC 04:** Submit Rental Registration
- **UC 14:** Send System Notifications
- **UC 15:** View System Notifications

### 👨‍💻 Module 4 (Member 4): Contracts & Reports (5 UCs)
- **UC 02:** Manage Rental Contracts
- **UC 20:** Terminate Rental Contracts
- **UC 13:** Manage Security Deposits
- **UC 19:** Configure System Settings
- **UC 03:** Generate Business Reports

---
*Bạn có thể copy trực tiếp bảng chia công việc này vào biên bản họp nhóm. Đây là cách chia rất chuẩn chỉ cho một project môn học.*
