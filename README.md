# Student Records Management System

A web-based Student Records Management System developed using **Spring Boot, Java, Thymeleaf, and MySQL** as part of the Software Construction and Development university assignment.

## Project Overview

The Student Records Management System allows users to manage student information through a web application. It provides functionality to add, view, search, update, and delete student records.

The application stores student information in a MySQL database and uses Spring Boot for backend development.

## Technologies Used

- Java 17
- Spring Boot
- Spring MVC
- Spring JDBC (JdbcTemplate)
- MySQL
- Thymeleaf
- HTML
- Maven
- Visual Studio Code

## Main Features

- Display all student records.
- Search students by name.
- View individual student details.
- Add new student records.
- Edit existing student information.
- Delete student records.
- Validate student information, including CGPA.
- Store student records in a MySQL database.

## Project Architecture

The application follows a layered architecture:

```text
Browser
   |
   v
StudentController
   |
   v
StudentService
   |
   v
StudentRepository
   |
   v
MySQL Database
```

### Main Components

- **Student.java:** Represents the student entity and its attributes.
- **StudentController.java:** Handles HTTP requests and application routes.
- **StudentService.java:** Contains the business logic.
- **StudentRepository.java:** Handles database operations using JdbcTemplate.
- **StudentrecordsApplication.java:** Main Spring Boot application class.

## Student Information

Each student record contains:

| Field | Description |
|---|---|
| ID | Unique student ID |
| Registration Number | Unique registration number |
| Name | Student name |
| Department | Student department |
| CGPA | Student CGPA |

## Database Setup

The application uses MySQL.

1. Open MySQL Workbench.
2. Execute the SQL script provided in this repository: `student_records.sql`.
3. This creates the required database and students table.
4. Update the database credentials in `application.properties`.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scd_records
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Replace `YOUR_PASSWORD` with your own MySQL password when running the application locally.

## How to Run the Project

### Prerequisites

- Java Development Kit (JDK) 17
- MySQL Server
- Maven (or the included Maven Wrapper)

### Steps

1. Clone or download this repository.
2. Open the project folder in Visual Studio Code.
3. Create the database by executing `student_records.sql` in MySQL Workbench.
4. Configure your MySQL username and password in `application.properties`.
5. Open the terminal in the project directory.
6. Run the following command:

**Windows:**

```bash
.\mvnw.cmd spring-boot:run
```

7. Open your browser and visit:

```text
http://localhost:8080/students
```

## Application Routes

| HTTP Method | Route | Function |
|---|---|---|
| GET | `/students` | Display all students |
| GET | `/students/{regNo}` | View student details |
| GET | `/students/new` | Open new student form |
| POST | `/students` | Add a student |
| GET | `/students/{regNo}/edit` | Open edit form |
| POST | `/students/{regNo}/update` | Update student |
| POST | `/students/{regNo}/delete` | Delete student |

## Project Purpose

This project demonstrates the practical implementation of Spring Boot web development, database connectivity using JdbcTemplate, MVC architecture, and CRUD (Create, Read, Update, Delete) operations.

---

**Project:** Student Records Management System  
**Course:** Software Construction and Development  
**Database:** MySQL  
**Backend Framework:** Spring Boot
