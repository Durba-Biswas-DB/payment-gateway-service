# Payment Gateway Service

Backend payment gateway built with Java + Spring Boot.

![CI](https://github.com/Durba-Biswas-DB/payment-gateway-service/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![Deployed on Render](https://img.shields.io/badge/Deploy-Render-46E3B7)

## Live Links
- Backend Repository: https://github.com/Durba-Biswas-DB/payment-gateway-service
- Frontend Repository: https://github.com/Durba-Biswas-DB/payment-gateway-ui
- Frontend App: https://payment-gateway-ui-five.vercel.app
- API Base URL: https://payment-gateway-service-uedm.onrender.com
- Health Check: https://payment-gateway-service-uedm.onrender.com/health
- Swagger UI: https://payment-gateway-service-uedm.onrender.com/swagger-ui/index.html
- OpenAPI JSON: https://payment-gateway-service-uedm.onrender.com/v3/api-docs

## Features
- Customer signup/login with persistent customer ID
- Create payment with unique transaction number
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

### Customer APIs
- `POST /api/v1/customers/signup`
- `POST /api/v1/customers/login`

#### Signup Request
```json
{
  "name": "Name",
  "email": "mail@example.com",
  "phone": "9876543210"
}
