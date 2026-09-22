# Rate Limiter — Low Level Design

A simple **Rate Limiter LLD implementation using Java and Spring Boot**, designed to demonstrate extensible object-oriented design using the **Strategy Design Pattern** and **Factory Pattern**.

The project focuses primarily on the **design and extensibility of the rate limiter**. The actual Bucket/Leaky Bucket algorithms are planned as future enhancements.

---

## 📌 Features

* REST APIs using Spring Boot
* Strategy Design Pattern for pluggable rate-limiting algorithms
* Factory Pattern for strategy creation
* Separation of Controller, Service, Rate Limiter, Strategy, and Factory
* Extensible design for adding different rate-limiting algorithms

---

## 🏗️ Project Structure

```text
src/main/java/com/example/rateLimiter
│
├── controller
│   └── RateLimiterController.java
│
├── services
│   └── RateLimiterService.java
│
└── models
    ├── RateLimiter.java
    ├── RateLimiterStrategy.java
    ├── RateLimiterStrategyfactory.java
    ├── BucketStrategy.java
    ├── LeakyStrategy.java
    └── Request.java
```

---

## 🧩 Design Overview

The main design goal is to keep the `RateLimiter` independent of the actual rate-limiting algorithm.

```text
                  Client
                    │
                    ▼
          RateLimiterController
                    │
                    ▼
          RateLimiterService
                    │
                    ▼
              RateLimiter
                    │
                    ▼
        RateLimiterStrategy
              /           \
             /             \
            ▼               ▼
   BucketStrategy     LeakyStrategy
     (planned)           (planned)
```

The Strategy Pattern allows different rate-limiting algorithms to be plugged into the core `RateLimiter` without changing its implementation.

---

# 🔹 Strategy Pattern

`RateLimiterStrategy` defines the contract that future rate-limiting algorithms will implement.

```java
public interface RateLimiterStrategy {

    public default String limitRequest(
            ArrayList<Request> requests,
            int capacity) {

        return "Default";
    }
}
```

The project currently contains placeholder strategy implementations to demonstrate the intended extension point.

### Planned Strategies

The following algorithms are intended to be implemented in future:

* **Token Bucket**
* **Leaky Bucket**
* **Fixed Window Counter**
* **Sliding Window Counter**
* **Sliding Window Log**

The current `BucketStrategy` and `LeakyStrategy` classes are **placeholders and do not represent complete implementations of the respective algorithms**.

---

# 🔹 Factory Pattern

The Factory is responsible for creating the appropriate strategy based on the configured strategy name.

```text
Strategy Name
      │
      ▼
RateLimiterStrategyFactory
      │
      ├── Bucket → BucketStrategy
      │
      └── Leaky  → LeakyStrategy
```

This separates strategy selection/object creation from the core `RateLimiter` logic.

---

# 🔹 RateLimiter

The `RateLimiter` maintains the configured capacity, incoming requests, and selected strategy.

```java
public class RateLimiter {

    public int capacity;

    public ArrayList<Request> requests = new ArrayList<>();

    public RateLimiterStrategy strategy;

    public RateLimiter(int capacity, String strategy) {

        this.capacity = capacity;

        this.strategy =
            new RateLimiterStrategyfactory(strategy).build();
    }

    public void addRequests(Request req) {
        requests.add(req);
    }

    public String limitRequest() {
        return strategy.limitRequest(requests, capacity);
    }
}
```

The important design decision is that `RateLimiter` depends on the **`RateLimiterStrategy` abstraction**, rather than directly depending on a specific algorithm.

---

# 🔹 Service Layer

The service layer acts as an abstraction between the REST controller and the rate limiter.

```java
@Service
public class RateLimiterService {

    public RateLimiter rateLimiter;

    public RateLimiterService() {
        this.rateLimiter =
            new RateLimiter(10, "Bucket");
    }

    public String addRequest(Request req) {

        rateLimiter.addRequests(req);

        return limitRequest();
    }

    public String limitRequest() {

        return rateLimiter.limitRequest();
    }
}
```

---

# 🔹 REST APIs

### Add Request

```http
GET /RateLimiter/AddRequest
```

Request body:

```json
{
    "Url": "/api/users",
    "time": 100
}
```

### Check Rate Limit

```http
GET /RateLimiter/LimitRequest
```

---

# 🔄 Request Flow

```text
Client
  │
  ▼
RateLimiterController
  │
  ▼
RateLimiterService
  │
  ▼
RateLimiter
  │
  ▼
RateLimiterStrategy
  │
  ▼
Rate Limit Decision
```

---

# 🎯 Design Patterns Used

## Strategy Pattern

The Strategy Pattern is used to make the rate-limiting algorithm interchangeable.

```text
RateLimiterStrategy
       │
       ├── BucketStrategy
       │
       ├── LeakyStrategy
       │
       └── Future Strategies
```

This makes it possible to introduce new algorithms without changing the core `RateLimiter`.

## Factory Pattern

The Factory Pattern is used to create the required strategy based on configuration.

This keeps strategy creation separate from the rate limiter's business logic.

---

# 🚧 Future Improvements

The current project focuses on demonstrating the **LLD structure and extensibility**. The following are planned improvements.

### 1. Implement Actual Rate-Limiting Algorithms

The current Bucket and Leaky strategies are placeholders. Future implementation will include:

* Token Bucket
* Leaky Bucket
* Fixed Window Counter
* Sliding Window Counter
* Sliding Window Log

### 2. Thread Safety

Handle concurrent requests safely using appropriate concurrent data structures and synchronization mechanisms.

### 3. Distributed Rate Limiting

Use Redis for maintaining rate-limit state across multiple application instances.

```text
              Load Balancer
                    │
          ┌─────────┼─────────┐
          ▼         ▼         ▼
       Server 1  Server 2  Server 3
          │         │         │
          └─────────┼─────────┘
                    ▼
                  Redis
```

### 4. Configurable Rate Limits

Move configuration such as capacity, refill rate, and strategy selection to application configuration instead of hard-coding them.

### 5. Per-User / Per-IP Limits

Support rate limiting based on:

* User ID
* API key
* IP address
* API endpoint

### 6. HTTP 429 Response

Return the standard:

```text
429 Too Many Requests
```

when a request exceeds the configured rate limit.

### 7. Testing

Add unit and integration tests for individual strategies and the complete request flow.

---

# 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Web
* REST API
* Maven
* Object-Oriented Design
* Strategy Design Pattern
* Factory Pattern

---

# 📚 Learning Objective

This project was built to practice designing a **Rate Limiter using Low-Level Design principles**.

The primary focus is on:

* Separation of responsibilities
* Programming to interfaces
* Strategy Pattern
* Factory Pattern
* Extensibility
* Clean object-oriented design

The actual rate-limiting algorithms are planned as future enhancements.

---

# 👩‍💻 Author

**Muskan Gupta**

Software Engineer | Backend Development

NIT Jamshedpur — Computer Science & Engineering
