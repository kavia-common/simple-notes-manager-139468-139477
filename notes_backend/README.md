# Notes Backend (Spring Boot)

A clean, modern Spring Boot REST API for managing notes (Ocean Professional style).

Features:
- CRUD endpoints under /notes
- In-memory H2 database with JPA
- Global exception handling
- OpenAPI/Swagger UI at /swagger-ui.html

Run:
- ./gradlew bootRun

API Examples:
- POST /notes
  { "title": "Meeting", "content": "Discuss roadmap" }
- GET /notes
- GET /notes/{id}
- PUT /notes/{id}
  { "title": "Updated", "content": "Updated content" }
- DELETE /notes/{id}
