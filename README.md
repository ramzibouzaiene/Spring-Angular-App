# Full-Stack Application with Spring Boot, Spring Security, and Angular

This repository contains a full-stack web application built using Spring Boot and Spring Security on the backend and Angular on the frontend. Both the backend and frontend applications are containerized using Docker, and Docker Compose is used to orchestrate the application.

## Project Structure

- **backend/**: Contains the Spring Boot application with Spring Security for backend services.
- **frontend/**: Contains the Angular application for the frontend.
- **docker-compose.yml**: Docker Compose file to build and run the backend and frontend services together.

---

## Features

- **Backend**:
  - Built with Spring Boot.
  - Security layer with Spring Security for authentication and authorization.
  - Exposes REST APIs consumed by the Angular frontend.
  - Token-based authentication (JWT).

- **Frontend**:
  - Built with Angular.
  - Communicates with the backend through HTTP requests.
  - Implements token-based authentication using JWT.

- **Docker**:
  - Dockerfiles for both backend and frontend.
  - Docker Compose for orchestrating the full-stack app with a single command.

---

## Prerequisites

- [Docker](https://www.docker.com/get-started) and [Docker Compose](https://docs.docker.com/compose/install/)
- Java 17 or later for the backend (if running without Docker)
- Node.js and npm for the frontend (if running without Docker)

---

## Getting Started

### Running the Application with Docker Compose

1. **Clone the repository**
   
   git clone https://github.com/ramzibouzaiene/Spring-Angular-App.git
   
   cd Spring-Angular-App
   
2. **Build and run the application: Run the following command to build and start both the backend and frontend services**
   
   docker-compose up --build
   
  This will:
  Build and start the backend service (Spring Boot).
  Build and start the frontend service (Angular).
  
3. **Access the application**

Frontend: Open your browser and navigate to http://localhost:4200.
Backend API: The backend will be available at http://localhost:8080.
