# Clinic Management API

A RESTful backend application for managing doctors, patients, and appointments in a clinic.

The project is built with Spring Boot and follows a layered architecture to provide a structured and maintainable backend.

## Features

* Doctor management
* Patient management
* Appointment management
* Appointment rescheduling
* Appointment cancellation
* Appointment completion
* Doctor-patient relationship lookup
* Search by name and specialization
* Pagination
* Sorting
* DTO-based API responses
* Request validation
* Global exception handling
* Custom exceptions

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

## Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

The project also uses DTOs and mappers to separate API request/response models from database entities.

Main packages:

```text
controller
service
repository
entity
dto
mapper
exception
config
```

## API Examples

### Create Appointment

**POST** `/api/v1/appointments`

Request:

```json
{
  "doctorId": 1,
  "patientId": 1,
  "date": "2026-09-20",
  "reason": "Regular medical checkup"
}
```

### Reschedule Appointment

**PATCH** `/api/v1/appointments/{id}/reschedule`

Request:

```json
{
  "date": "2026-09-25"
}
```

### Change Appointment Status

**PATCH** `/api/v1/appointments/{id}/status?status=CANCELLED`

Possible appointment statuses:

```text
BOOKED
CANCELLED
COMPLETED
```

### Search Doctors

**GET** `/api/v1/doctors/search?name=Kamal`

Pagination and sorting can also be used:

```text
GET /api/doctors/search?page=0&size=10&sort=name,asc
```

### Search Patients

**GET** `/api/v1/patients/search?name=Kamal`

### Get Patients Associated With a Doctor

**GET** `/api/v1/doctors/{id}/patients`

This endpoint returns the doctor together with patients who have appointments with that doctor.

## Validation and Exception Handling

The API uses Jakarta Bean Validation for validating incoming requests.

Examples include:

* `@NotNull`
* `@NotBlank`

Custom exceptions are used for cases such as:

* Doctor not found
* Patient not found
* Appointment not found

A global exception handler provides appropriate HTTP responses for these errors.

## Database

The application uses MySQL with Spring Data JPA for persistence.

For local development, the application can be configured to use a local MySQL/XAMPP database.

Database credentials should be provided through environment variables rather than committed to the repository.

## Running the Project

### Prerequisites

* Java
* Maven
* MySQL

### Steps

1. Clone the repository.
2. Create a MySQL database.
3. Configure the database connection.
4. Run the application using Maven or IntelliJ IDEA.

Example:

```bash
mvn spring-boot:run
```

## Authentication

JWT-based authentication and role-based authorization are planned as the next development stage.

The current version focuses on the core clinic management functionality.

## Future Improvements

* JWT authentication
* Role-based authorization
* API documentation with Swagger/OpenAPI
* Improved appointment scheduling rules
* Frontend application
* Automated tests
* Deployment

## Project Status

Currently under active development.
