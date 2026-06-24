Markdown
# 🎫 Ticket Management REST API - Cheat Sheet & Guide

This document provides a comprehensive architectural overview, configuration patterns, validation techniques, and repository best practices for the Ticket Management REST API.

---

## 📁 1. Project Configuration & Directory Architecture

To ensure your application initializes properly and scans all components without configuration errors, maintain a clean package hierarchy:

src/main/java/org/ticket/restapi/
│
├── RestapiApplication.java (Main Boot class)
│
├── controller/
│   ├── TicketController.java
│   └── GlobalExceptionHandler.java (Crucial: Must be in a scanned package)
│
├── service/
│   ├── TicketService.java (Interface)
│   └── TicketServiceImpl.java (Implementation)
│
├── repository/
│   └── TicketRepository.java
│
├── entity/
│   └── Ticket.java (Database Entity mapped with JPA)
│
└── entity/dto/
├── TicketDTO.java (Data Transfer Object payload)
└── ErrorResponse.java (Custom JSON Exception format)


> ⚠️ **Exam Warning:** If you explicitly override `scanBasePackages` in your main class, make sure any package containing controllers, services, or exception handlers is manually listed. Alternatively, remove explicit package declarations to let Spring default to scanning everything downstream from your main application package.

---

## 🗄️ 2. Core Java ↔ SQL Hibernate Mappings

Ensure absolute uniformity between your data schemas and Java models to eliminate serialization anomalies:

* **Primary Key IDs:** Match database `INT AUTO_INCREMENT` or `BIGINT` to Java wrapper objects like `Integer` or `Long`. The wrapper class must match the generic identity specified in your repository: `JpaRepository<Ticket, Long>`.
* **Timestamps:** Map `DATETIME` columns directly to modern `java.time.LocalDateTime` objects instead of using legacy `java.util.Date`.
* **String Attributes:** Map `VARCHAR(X)` columns directly to standard `String` objects.
* **Enumerations:** Annotate enum types with `@Enumerated(EnumType.STRING)` so strings like `"OPEN"` are stored in your database instead of numeric indexes (`0`, `1`).

---

## 🔄 3. Data Transformation Layer (Service & DTOs)

Keep your architecture decoupled by preventing raw database entity configurations from leaking directly into your presentation or controller endpoints.

* **Use `BeanUtils.copyProperties`:** Avoid writing dozens of standard boilerplate getters and setters. Execute `BeanUtils.copyProperties(source, target)` to rapidly transfer properties across compatible fields during both input creation (`POST`) and extraction (`GET`).
* **Preserve State Invariants:** Use your service layer implementations to apply default states, such as setting system creation times using `LocalDateTime.now()` or assigning base statuses like `Status.OPEN` automatically before invoking `.save()`.
* **Prevent Primitive Mismatch Crashes:** Ensure your service signatures utilize wrapper tracking references consistently with your repository models to avoid compile-time interface compatibility errors.

---

## 🌐 4. REST Controller Standard Routing Layout

Build clean, uniform controller paths that leverage HTTP verbs to identify specific actions. Avoid naming endpoints after operations (e.g., use `/api/tickets` instead of `/saveTicket`).

### Standard HTTP Mapping Reference Matrix

| Real-World Use Case | HTTP Verb | Target Endpoint Pattern | Expected Status (Success) | Expected Status (Failure/Empty) |
| :--- | :--- | :--- | :--- | :--- |
| **Persist/Create Entry** | `POST` | `/api/tickets` | `201 Created` | `400 Bad Request` (Duplicate Data) |
| **Fetch Asset by ID** | `GET` | `/api/tickets/{id}` | `200 OK` | `404 Not Found` (Missing ID) |
| **Update Existing Fields** | `PUT` | `/api/tickets/{uniqueKey}` | `200 OK` | `404 Not Found` (Asset Missing) |
| **Fetch All Open Entities**| `GET` | `/api/tickets/open` | `200 OK` (Array) | `204 No Content` (Empty Collection) |

---

## 🚨 5. Enterprise Global Exception Interception Engine

Instead of scattering messy `try-catch` structures inside your individual controller methods, handle failures globally using a centralized aspect wrapper.

### Step 1: Create a Structured Error Format (`ErrorResponse.java`)

```java
public class ErrorResponse {
    private java.time.LocalDateTime timestamp = java.time.LocalDateTime.now();
    private int status;
    private String error;
    private String message;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
    // Implement standard getter methods
}
Step 2: Build the Interceptor (GlobalExceptionHandler.java)
Java
@org.springframework.web.bind.annotation.RestControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public org.springframework.http.ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        ErrorResponse err = new ErrorResponse(400, "Bad Request Logic Failure", ex.getMessage());
        return org.springframework.http.ResponseEntity.badRequest().body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(java.util.NoSuchElementException.class)
    public org.springframework.http.ResponseEntity<ErrorResponse> handleNotFound(java.util.NoSuchElementException ex) {
        ErrorResponse err = new ErrorResponse(404, "Resource Not Found", ex.getMessage());
        return org.springframework.http.ResponseEntity.status(404).body(err);
    }
}
🛠️ 6. High-Speed Debugging & Recovery Guide
If you run into issues during your practical test window, use these quick troubleshooting steps:

Eclipse/IDE False Positives (Red lines on clean code): Right-click your project directory and choose Maven → Update Project, checking the "Force Update of Snapshots/Releases" box. Alternatively, go to the top menu and select Project → Clean.

White-Label Error Instead of Custom JSON: Verify that your GlobalExceptionHandler is inside a package that Spring is actively scanning.

Database Deadlocks / Connection Resets: Make sure your local MySQL instance is running and that your application.properties credentials match your database instance exactly.


### Why this works now:
1. **Code Blocks Saved:** It places the Java classes inside triple backticks (\`\`\`) with the `java` label, ensuring syntax highlighting works on GitHub/Eclipse.
2. **Proper Directory Tree:** The ASCII folder tree structure is nested safely within a clean layout.
3. **Escaped Characters:** Any mathematical or formatting markers that break Markdown parsers have been cleanly isolated.
