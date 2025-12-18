# Spring Boot Online Payment with Virtual Threads

## 📌 Project Description

This project is a **reference implementation of an online payment processing system** built with **Java 25 and Spring Boot 3**, using **Virtual Threads (Project Loom)** to handle high-concurrency, I/O‑bound workloads efficiently.

The system follows **Clean Architecture**, clearly separating:

* **Domain** (business rules)
* **Application** (use cases)
* **Infrastructure** (DynamoDB, WireMock, Feign)
* **API** (REST controllers)

It simulates a real payment flow including:

* Idempotent payment processing
* Fraud validation
* External payment gateway integration
* Persistent storage in DynamoDB

The project also includes a **Gatling load tests** to compare **platform threads vs virtual threads** under heavy load.

---

## 🧱 Architecture Overview

```
API (REST)
   ↓
Application (Use Cases)
   ↓
Domain (Entities + Ports)
   ↓
Infrastructure (DynamoDB, WireMock, Feign)
```

This structure ensures:

* High testability
* Independence from frameworks
* Easy replacement of infrastructure components

---

## 🚀 How to Run the Project

### 1️⃣ Prerequisites

* Java **25**
* Docker & Docker Compose
* Gradle

---

### 2️⃣ Start Infrastructure (DynamoDB + WireMock)

```bash
docker-compose up
```

This will start:

* **DynamoDB Local** on port `4566`
* **WireMock** on port `8080`

Tables in DynamoDB are **created automatically on application startup**.

---

### 3️⃣ Run the Application

```bash
./gradlew bootRun
```

The API will be available at:

```
POST http://localhost:8080/payments
```

Example request:

```bash
curl -X POST "http://localhost:8080/payments?orderId=ORD-1&amount=100"
```

---

### 4️⃣ Run Load Tests (Gatling)

```bash
./gradlew gatlingRun
```

The test simulates **10,000 concurrent payment requests**, exercising the full stack:

* REST API
* DynamoDB
* WireMock gateways

Reports are generated automatically in:

```
build/reports/gatling
```

---

## 🧪 Virtual Threads Comparison

You can compare execution modes by toggling:

```properties
spring.threads.virtual.enabled=true
```

or

```properties
spring.threads.virtual.enabled=false
```

Run the Gatling tests in both modes and compare:

* Throughput
* Latency (P95 / P99)
* Error rate
* CPU utilization

---

## 📊 Performance Comparison Graphs

> 📈 **Add your comparison charts here**

Suggested graphs:

* Requests per second (VT vs Platform Threads)
* Latency distribution
* Error rate under load

*(Insert images or links to Gatling reports)*

---

## 🛠️ Technologies Used

* **Java 25** (Virtual Threads / Project Loom)
* **Spring Boot 3.x**
* **Spring Web (MVC)**
* **OpenFeign**
* **AWS SDK v2 (DynamoDB)**
* **DynamoDB Local**
* **WireMock**
* **Docker & Docker Compose**
* **Gatling** (Load Testing)
* **JUnit 5**
* **Testcontainers**

---

## 🎯 Key Takeaways

* Virtual Threads dramatically improve scalability for blocking I/O
* Clean Architecture keeps business logic isolated and testable
* Synchronous code can scale without reactive complexity
* The system is production‑ready and extensible

---

## 📎 Next Improvements (Optional)

* Resilience4j (retry, timeout, circuit breaker)
* OpenTelemetry tracing
* Contract testing with Pact
* Real AWS deployment
* Async outbox / event streaming

---

✅ This project is designed as a **learning tool and production blueprint** for modern Java concurrency with Spring.
