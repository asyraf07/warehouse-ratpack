# Whizware
## Overview
This project is inventory management application built using the Ratpack framework and Java 17. It features JWT-based authentication and authorization, custom exception handling, and interactions with Hibernate for database operations.

## Features
- Authentication & Authorization: Secure your application using JWT tokens with custom claims and roles.
- Custom Middleware: Implement middleware to check for authorization and handle errors efficiently.
- Domain Objects: Manage entities such as items, orders, and users with the help of Hibernate.
- DTOs and Response Objects: Clean and maintainable data transfer and response handling.
## Prerequisites
- Java 17
- Gradle
- Postgresql 16
- IntelliJ IDEA
## Getting Started
### Clone the Repository
```bash
git clone https://github.com/yourusername/ratpack-app.git
cd ratpack-app
```
### Build the Project
```bash
./gradlew clean build
```
### Run the Application
You can run the program from Intellij IDEA, or using this command.
```bash
java -jar target/ratpack-app.jar
```
## Configuration
### Application Properties
Configure your database connection and other settings in the application.yaml file:

```yaml
# Database configuration 
database:    
  url: jdbc:postgresql://localhost:5432/example
  username: postgres
  password: example

# Hibernate configuration
hibernate:    
  showSql: true
  formatSql: true
  ddlAuto: update
```
## Usage
### Authentication
Use JWT tokens for authentication. Tokens must be included in the Authorization header as Bearer <token>.

### Error Handling
Custom exceptions are used to handle errors gracefully. Refer to the exception package for more details.
