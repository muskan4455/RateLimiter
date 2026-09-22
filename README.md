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
