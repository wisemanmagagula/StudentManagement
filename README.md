
# Angular 17 Frontend with Java 17 Spring Boot Backend Setup

This repository contains an Angular 17 frontend and a Java 17 Spring Boot backend API.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Node.js and npm**: Required for building and running the Angular frontend.
  - Install Node.js version 20: [Node.js](https://nodejs.org/)
  
- **Java Development Kit (JDK)**: Needed for compiling and running the Java 17 Spring Boot backend.
  - Install JDK version 17: [JDK Installation](https://adoptopenjdk.net/)
  
- **Maven**: Build automation tool for Java projects.
  - Install Maven: [Maven](https://maven.apache.org/download.cgi)
  
- **Docker**: Optional for running the application in containers.
  - Install Docker: [Get Docker](https://docs.docker.com/get-docker/)

## Project Structure

- `studentmanagement-frontend/`: Angular application
- `studentmanagement-backend/`: Java API

## Backend Setup

### 1. Clone the Repository

```bash
git clone [<repository_url>](https://github.com/wisemanmagagula/StudentManagement)
cd StudentManagement
```

### 2. Build and Run the Java 17 Spring Boot Backend Locally

#### 2.1 Navigate to the Backend Directory

```bash
cd studentmanagement-backend/
```

#### 2.2 Build the Backend

```bash
mvn clean package
```

#### 2.3 Run the Backend

```bash
java -jar target/backend.jar
```

The backend API will start on `http://localhost:8080`.

## Frontend Setup

### 1. Navigate to the Frontend Directory

```bash
cd ../studentmanagement-frontend/
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Run the Frontend Locally

```bash
npm start
```

The Angular frontend will start on `http://localhost:4200`.

## Docker Setup (Optional)

If you prefer to run the application using Docker containers, follow these steps.

### 1. Build and Run with Docker

#### 1.1 Build the Backend Docker Image

```bash
cd ../studentmanagement-backend/
docker build -t backend-api .
```

#### 1.2 Run the Backend Docker Container

```bash
docker run -d -p 8080:8080 --name backend-container backend-api
```

#### 1.3 Build the Frontend Docker Image

```bash
cd ../studentmanagement-frontend/
docker build -t frontend-app .
```

#### 1.4 Run the Frontend Docker Container

```bash
docker run -d -p 80:80 --name frontend-container frontend-app
```

### 2. Accessing the Application

- **Backend API**: Access the Java backend API at `http://localhost:8080`.
- **Frontend**: Access the Angular frontend at `http://localhost`.

## Additional Notes

- **Customization**: Modify environment variables and configurations as needed.
- **Troubleshooting**: Refer to Docker logs (`docker logs <container_name>`) for debugging.
