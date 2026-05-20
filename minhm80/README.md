# minhm80 - CRUD POS Backend

Dự án backend CRUD cho hệ thống POS: quản lý store, branch, employee, category, product, customer, order, inventory, refund, shift.

## Công nghệ
- Java 21
- Spring Boot 3.5.9
- Spring Web
- Spring Security + JWT
- Spring Data JPA
- MySQL 8
- Maven
- Swagger/OpenAPI

## Yêu cầu
- JDK 21
- MySQL 8
- Tạo database: `pos`

## Cấu hình chính
Trong `src/main/resources/application.properties`:
- Port: `5000`
- Datasource mặc định:
  - `jdbc:mysql://localhost:3306/pos`
  - user: `root`
  - password: `root`

Có thể override bằng env:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## Cách chạy
Từ thư mục `minhm80`:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Truy cập
- Base URL: `http://localhost:5000`
- Swagger UI: `http://localhost:5000/swagger-ui/index.html`
