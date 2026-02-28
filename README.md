# Payment Gateway Service

Backend payment gateway built with Java + Spring Boot.

![CI](https://github.com/Durba-Biswas-DB/payment-gateway-service/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![Deployed on Render](https://img.shields.io/badge/Deploy-Render-46E3B7)


## Live Links
- API Base URL: https://payment-gateway-service-uedm.onrender.com
- Swagger UI: https://payment-gateway-service-uedm.onrender.com/swagger-ui/index.html
- OpenAPI JSON: https://payment-gateway-service-uedm.onrender.com/v3/api-docs

## Features
- Create payment with unique transaction number
- Customer ID and amount handling
- QR code generation (Base64 PNG)
- Complete payment flow with status updates
- Invoice JSON generation
- PDF invoice download
- Status tracking: `CREATED`, `SUCCESS`, `FAILED`

## Tech Stack
- Java 17
- Spring Boot
- Spring Web (REST APIs)
- Spring Data JPA / Hibernate
- PostgreSQL (Neon)
- ZXing (QR generation)
- iText (PDF generation)
- JUnit + MockMvc (tests)
- GitHub Actions (CI)
- Render (deployment)

## Project Structure
- `controller` - REST endpoints
- `service` - business logic
- `repository` - DB access
- `entity` - JPA entities
- `dto` - request/response models
- `util` - QR, transaction, PDF utilities
- `exception` - global exception handling
- `config` - OpenAPI config

## API Endpoints

### 1) Create Payment
- `POST /api/v1/payments`
- Request:
```json
{
  "customerId": "CUST-1001",
  "amount": 499.99
}
```

### 2) Complete Payment
- `POST /api/v1/payments/complete`
- Request:
```json
{
  "transactionNumber": "TXN-20260223-ABC123"
}
```

### 3) Get Invoice JSON
- `GET /api/v1/payments/invoice/{transactionNumber}`


### 4) Download Invoice PDF
- `GET /api/v1/payments/invoice/{transactionNumber}/pdf`


## Local Run

### Prerequisites
- Java 17
- Maven
- PostgreSQL database (Neon/local)

### Run command
```bash
DB_HOST=<your_host> \
DB_PORT=5432 \
DB_NAME=<your_db_name> \
DB_USER=<your_db_user> \
DB_PASSWORD=<your_db_password> \
mvn spring-boot:run
```


## Test
```bash
mvn test
```

## CI
GitHub Actions workflow runs tests on every push/PR to main.