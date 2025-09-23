# **Labseq Sequence**

This project simplifies the startup for the Labseq Sequence, offering a full-stack solution with a Quarkus backend (Java), in-memory caching, and an Angular frontend, all orchestrated with Docker.

## Features

- **Quarkus Backend:** Implement the Labseq algorithm with a robust and scalable Quarkus backend.
- **Redis Caching:** Optimize performance with distributed caching using Redis to efficiently store and retrieve sequence values across containers.
- **Angular Frontend:** Interact seamlessly with the LabSeq sequence through an intuitive Angular frontend.
- **Docker Orchestration:** Easily deploy and manage the entire solution using Docker.

## Getting Started

### **Requirements**

- Maven
- Java 21
- Docker

### **Instructions**

To start the application, navigate to the project directory and run the following Docker commands:

```shell
docker-compose up --build
```

## **How to Use**

#### Open the Application in Your Browser
To interact with the LabSeq sequence, open your browser and go to [http://localhost:4200](http://localhost:4200) after starting the application.
#### Using the REST API (Swagger UI)
For interactive API exploration and testing, open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) in your browser. Swagger UI provides a graphical interface to try out all available endpoints and view their documentation.

#### Using Curl (Command Line)
You can also retrieve the calculated value of the LabSeq sequence at a specified index directly from the command line. Replace `${n}` with the desired index value:

```shell
curl -X GET "http://localhost:8080/labseq/${n}"
```

## API Endpoints

The backend exposes a REST API for retrieving LabSeq sequence values. Below are the available endpoints:

| Method | Path                | Description                                      |
|--------|---------------------|--------------------------------------------------|
| GET    | /labseq/{n}         | Returns the LabSeq value for the given index `n` |

### GET /labseq/{n}

- **Description:** Returns the LabSeq value for the specified non-negative integer index `n`.
- **Path Parameter:**
  - `n` (integer, required): The index in the LabSeq sequence (must be >= 0).
- **Responses:**
  - `200 OK`: Returns the LabSeq value as a number.
    - Example: `2`
  - `400 Bad Request`: Returned if the input is invalid (e.g., negative index). Returns a JSON error object.
    
  - `500 Internal Server Error`: Returned if an unexpected error occurs. Returns a JSON error object.

