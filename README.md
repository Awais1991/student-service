# Student Service

Student Service is a Java application developed using Spring Boot (version 3.2.2) and Java 21. It serves as a backend service for handling students management, grade management add school management, The application utilizes an H2 database for data storage.

## Technologies

- Java 21
- Spring Boot 3.2.2
- H2 Database
- JUnit
- Swagger for API documentation
- Spring Boot Actuator for monitoring and managing application

## Features

1. **Add School:** The service allows to add and update schools.

2. **Add Grades:** The service allows to add and update schools.

3. **Student Management:**
    - Enrol new student: Enrol new Student
    - Update Existing enrolled student.

4. **Swagger Documentation:**
    - API documentation is available through Swagger for easy exploration and integration.
    - Accessible at [http://localhost:8081/student/swagger-ui/index.html#/](http://localhost:8081/student/swagger-ui/index.html#/).
### Spring Boot Actuator

- Monitor and manage the application in production with Spring Boot Actuator.
- Endpoints include health, metrics, info, and more.
- Accessible at [http://localhost:8081/student/actuator](http://localhost:8081/student/actuator).

## Unit Test Cases

- JUnit test cases are implemented to ensure the reliability and correctness of the application.

## Setup

1. Clone the repository.
2. Configure your IDE or build tool for a Spring Boot application.
3. Run the application and access the Swagger documentation to explore the APIs.


## Endpoints

- **Student Enrollment:**
    - POST `/api/students/enroll` - Enrol the student with given information, return roll no in response.

- **Student Management:**
    - PUT `/api/students/rollNo/{rollNo}` - Update existing student for a specific student roll number.
    - GET `/api/students/rollNo/{rollNo}` - Get student information for a specific given roll number.
    - GET `/api/students/{gradeId}` - Get list of All students for a given grade.

- **School and Grades:**
    - GET `/api/schools/{uuid}` - Retrieve school detail for a given school unique identifier
    - POST `/api/schools` - create a new School in the system
    - GET `/api/schools` - Get all schools available in the system
    - GET `/api/grades` - Get all grades available in the system
    - GET `/api/grades/{uuid}` - Retrieve grade information for a given grade unique identifier
    - POST `/api/grades` - Create a grade in the system based on the given information

# Getting Started

To get started with the project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/Awais1991/student-service.git

2. Build Application:

   ```bash
   mvn clean install

3. Run Application:

   ```bash
   java -jar studentService-0.0.1-SNAPSHOT.jar

4. Access Swagger:

   ```bash
   http://localhost:8081/student/swagger-ui/index.html#/