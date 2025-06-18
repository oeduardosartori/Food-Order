# 🛒 Food Order API

[![CI](https://github.com/oeduardosartori/Food-Order/actions/workflows/ci.yml/badge.svg)](https://github.com/oeduardosartori/Food-Order/actions)

A RESTful API for managing orders, simulating the backend of a small e-commerce platform. The project was developed to apply best backend practices, including software architecture, testing, Git, and continuous integration.

---

## 📌 Project Goals

- Review and apply backend development fundamentals with Java
- Demonstrate technical proficiency using Spring Boot, tests, and CI/CD
- Create a robust, clean, testable, and well-documented API
- Automate build and test execution via GitHub Actions

---

## ⚙️ Tech Stack

| Category        | Stack                                                        |
|----------------|--------------------------------------------------------------|
| Language        | Java 17                                                      |
| Framework       | Spring Boot                                                  |
| Build Tool      | Maven                                                        |
| Database        | MySQL (or PostgreSQL)                                        |
| ORM             | Spring Data JPA                                              |
| Documentation   | Swagger / OpenAPI                                            |
| Tests           | JUnit 5 + Mockito                                            |
| Validation      | Bean Validation (JSR-380)                                    |
| CI              | GitHub Actions                                               |
| Architecture    | DTOs, Services, SOLID principles, Clean Code, Exception Handling |

---

## 📦 Features

| Entity   | Functionality                                              |
|----------|------------------------------------------------------------|
| Client   | Full CRUD                                                  |
| Product  | Full CRUD                                                  |
| Order    | Create order with items and automatic total calculation    |
| Order    | List orders by client                                      |
| Order    | Update order status                                        |

---

## 🧱 Project Structure

```
src/
└── main/
└── java/com/sartori/food_order
├── config
├── controller
├── dto
├── entity
├── exception
├── helper
├── mapper
├── repository
├── service
└── validator
```
---

## 🔄 Main Endpoints

> Full documentation available via Swagger at `/swagger-ui.html`

### Clients
- `GET /clients`
- `GET /clients/{id}`
- `POST /clients`
- `PUT /clients/{id}`
- `DELETE /clients/{id}`

### Products
- `GET /products`
- `GET /products/{id}`
- `POST /products`
- `PUT /products`
- `DELETE /products/{id}`

### Orders
- `POST /orders`
- `GET /orders/client/{clientId}`
- `PATCH /orders/{id}/status`

---

## 🚀 How to Run Locally

### Requirements

- Java 17+
- Maven
- MySQL or PostgreSQL running locally

### Database Configuration

Update your `src/main/resources/application.properties` file with your database connection:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/food_order
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
---

## Build and Run

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/YOUR_REPO.git

# Enter the project folder
cd food-order-api

# Run tests and build
mvn clean verify

# Start the application
mvn spring-boot:run

```
---

## Testing

The project uses JUnit 5 and Mockito for unit testing. Tests focus primarily on the `service` layer.

```bash

mvn test

```

Test reports are available in:

```bash

target/surefire-reports/

```

---

## CI - Continuous Integration

CI - Continuous Integration
- Build on every push or pull request
- Unit test execution
- Artifact upload (JAR and test reports)

Workflow file: `.github/workflows/ci.yml`

--- 

## Future Improvements

- Test coverage with JaCoCo
- Integration tests with Testcontainers
- Authentication and authorization (Spring Security)
- Automated deploy to Railway / Fly.io / Render
- Pagination and sorting on list endpoints
- Slack/Webhook notifications for CI
- Monitoring with Spring Actuator + Prometheus

---

## Author
**Eduardo Sartori**

Backend Developer Pleno | Disciple of Jesus 🙏

[LinkedIn](https://www.linkedin.com/in/oeduardosartori) • [GitHub](https://github.com/oeduardosartori)

---

## License
This project is licensed under the MIT License. See the LICENSE file for more details.
