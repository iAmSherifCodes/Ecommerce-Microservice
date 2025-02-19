# Ecommerce Microservices Documentation

# Microservices Documentation

## Overview

This documentation provides an overview of the microservices architecture, features, API endpoints, and tools used in the system. The project consists of multiple microservices communicating through Eureka discovery service and an API Gateway.

## Features

- **Service Discovery**: Uses Eureka for registering and discovering services dynamically.
- **API Gateway**: Routes client requests to appropriate microservices.
- **Inter-Service Communication**: Uses FeignClient for efficient service-to-service calls.
- **Logging and Monitoring**: Integrated with SLF4J for logging.
- **Docker Support**: Can be containerized and orchestrated with Docker Compose.
- **Database Integration**: In-Memory data storage.

## Tools and Technologies Used

- **Spring Boot** - Java Framework for building microservices.
- **Spring Cloud Netflix Eureka** - Service discovery and registry.
- **Spring Cloud API Gateway** - API Gateway for routing requests.
- **FeignClient** - Declarative HTTP client for inter-service communication.
- **SLF4J** - Logging framework.
- **Docker & Docker Compose** - Containerization and orchestration.

## API Endpoints

### API Gateway ([http://localhost:8084](http://localhost:8084))

|  Endpoint   | Description          |
| ------------| -------------------- |
|  /product/** | Fetch product details |
|  /order/**  | Fetch order details   |

### Product Service ([http://localhost:8081](http://localhost:8081))

| Method | Endpoint      | Description            |
| ------ |---------------| ---------------------- |
| GET    | /product      | Get all products       |
| GET    | /product/{id} | Get product by ID      |
| POST   | /product/add  | Create a new product   |
| PUT    | /product/{id} | Update product details |
| DELETE | /product/{id} | Delete a product       |

### Order Service ([http://localhost:8082](http://localhost:8082))

| Method | Endpoint  | Description |
| ------ |-----------| --------- |
| GET    | /order    | Get all orders |
| GET    | /order/{id} | Get order by ID |
| POST   | /order    | Create a new order |

## API Documentation

#### There are two controllers User and Post

# USER API

## Create User
- This function creates new users in the platform.
-
POST (https://{hostname}/users/register)


### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
    {
        "username": "John Doe",
        "password": "myP@ssw0rd"
    }

### Sample Response Body
    {
    "success": true,
    "data": {
        "username": "idris doe",
        "password": "$2b$10$GdZ6nFcNFeJQfOb0CrY7fuyqS6Lw4UBYdzKbs8SGjCrRFGb0vJ0tm",
        "salt": "$2b$10$GdZ6nFcNFeJQfOb0CrY7fu",
        "followers": [],
        "notifications": [],
        "createdAt": "2024-04-11T17:42:52.758Z",
        "updatedAt": "2024-04-11T17:42:52.758Z",
        "id": "6618211c54b573b9baea8cc4"
    }
    }

## Login User
- This function logs users in the platform.
-
POST (https://{hostname}/users/login)

### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
    {
        "username": "John Doe",
        "password": "myP@ssw0rd"
    }

### Sample Response Body
    {
        "success": true,
        "data": {
            "username": "John Doe",
            "salt": "$2b$10$SZAP/znq82HNOH15C6Ahi.",
            "followers": [],
            "createdAt": "2024-04-11T09:32:49.350Z",
            "updatedAt": "2024-04-11T09:32:49.350Z",
            "id": "6617ae4125e9421cf1a316ba",
            "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InJvcWVlYiIsImlkIjoiNjYxN2FlNDEyNWU5NDIxY2YxYTMxNmJhIiwiaWF0IjoxNzEyODI5NzYzLCJleHAiOjE3MTI5MTYxNjN9.TFBZ9Ofwalbv9ICtvKCv6Gwr-HL1Ec83C8tOxcOFvQw"
        }
    }


## Follow User
- This function allows users to follow other users on the platform.
  POST (https://{hostname}/users/follow)

### Sample Request Header
    'Content-Type: application/json'
    'Authorization: Bearer  <Bearer Token>'

### Sample Request Body
    {
        "userId": "661788b14967229067b49cc8",
        "followerId": "6617889f4967229067b49cc2"
    }

### Sample Response Body
    {
        "success": true,
        "data": {
            "username": "john doe",
            "password": "$2b$10$oi6dVqO8eK7e.79DiqY1X.woFhsAR1c2IqSMsINcHxr2mRP4/1fYO",
            "salt": "$2b$10$oi6dVqO8eK7e.79DiqY1X.",
            "followers": [
                {
                    "username": "janet doe"
                }
            ],
            "createdAt": "2024-04-11T16:59:07.187Z",
            "updatedAt": "2024-04-11T16:59:54.135Z",
            "id": "661816dbca2b2ac2493316d5"
        }
    }

# POST API

AUTH

## Create Post
- This function creates a new post in the platform.
  POST (https://{hostname}/posts/post)

### Sample Request Header
    'Content-Type: application/json'
    'Authorization: Bearer  <Bearer Token>'
### Sample Request Body
    {
        "userId": "661816dbca2b2ac2493316d5",
        "description": "Love with your brain"
    }

### Sample Response Body
    {
        "success": true,
        "data": {
            "userId": "661816dbca2b2ac2493316d5",
            "description": "Love with your brain",
            "likes": [],
            "comments": [],
            "createdAt": "2024-04-11T17:02:53.492Z",
            "updatedAt": "2024-04-11T17:02:53.492Z",
            "id": "661817bdca2b2ac2493316e1"
        }
    }

## Like Post
- This function allows a user to like a post on the platform.
  POST (https://{hostname}/post/like)

### Sample Request Header
    'Content-Type: application/json'
    'Authorization: Bearer  <Bearer Token>'

### Sample Request Body
    {
    "postId": "661817bdca2b2ac2493316e1",
    "userId": "661816dbca2b2ac2493316d5"
    }

### Sample Response Body
    {
       "success": true,
       "data": {
           "userId": "661816dbca2b2ac2493316d5",
           "description": "Love with your brain",
           "likes": [
               {
                   "username": "you"
               }
           ],
           "comments": [],
           "createdAt": "2024-04-11T17:02:53.492Z",
           "updatedAt": "2024-04-11T17:10:02.582Z",
           "id": "661817bdca2b2ac2493316e1"
       }
    }

## Comment Post
- This function allows users on the platform to comment on a post on the platform.
-
POST (https://{hostname}/posts/comment)

### Sample Request Header
    'Content-Type: application/json'
    'Authorization: Bearer  <Bearer Token>'

### Sample Request Body
    {
    "postId": "661817bdca2b2ac2493316e1",
    "userId": "661816dbca2b2ac2493316d5",
    "comment": "Like this post!"
    }

### Sample Response Body
    {
        "success": true,
        "data": {
            "userId": "661816dbca2b2ac2493316d5",
            "description": "Love with your brain",
            "likes": [
                {
                    "username": "you"
                }
            ],
            "comments": [
                {
                    "comment": "Like this post!",
                    "author": "john doe"
                }
            ],
            "createdAt": "2024-04-11T17:02:53.492Z",
            "updatedAt": "2024-04-11T17:12:17.133Z",
            "id": "661817bdca2b2ac2493316e1"
        }
    }


## View Number Of Likes
- This function allows users on the platform to view the number of likes on a post on the platform.
-
POST (https://{hostname}/posts/viewNumberOfLikes)

### Sample Request Header
    'Content-Type: application/json'
    'Authorization: Bearer  <Bearer Token>'

### Sample Request Body
    {
    "postId": "661817bdca2b2ac2493316e1",
    }

### Sample Response Body
    {
    "success": true,
    "data": 1
    }


## View Comment
- This function allows users on the platform to view comment of a post on the platform.
-
POST (https://{hostname}/posts/viewComments)

### Sample Request Header
    'Content-Type: application/json'
    'Authorization: Bearer  <Bearer Token>'

### Sample Request Body
    {
    "postId": "661817bdca2b2ac2493316e1",
    }

### Sample Response Body
    {
        "success": true,
        "data": {
            "results": [
                {
                    "comment": "Like this post!",
                    "author": "john doe"
                }
            ]
        }
    }



### Fetch Product by ID

#### Request:

```sh
curl -X GET http://localhost:8082/products/{id}
```

#### Response:

```json
{
  "id": "abc123",
  "name": "Laptop",
  "price": 1200.00,
  "quantity": 10
}
```

### Create an Order

#### Request:

```sh
curl -X POST http://localhost:8081/orders \
  -H "Content-Type: application/json" \
  -d '{"productId": "abc123", "quantity": 2}'
```

#### Response:

```json
{
  "orderId": "xyz789",
  "productId": "abc123",
  "quantity": 2,
  "status": "CONFIRMED"
}
```


***
## Getting Started
This document provides instructions on how to run the microservices locally, including necessary environment setup details.
## Prerequisites
Ensure you have the following installed on your system:
- [Java 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/)
- [Postman](https://www.postman.com/) or `curl` for testing APIs

## Running Services Locally
You can run the services locally with or without Docker. If you do not have Docker installed on your machine, consider running the services without Docker.

## Without Docker
### 1. Clone the Repository
```sh
 git clone <https://github.com/iAmSherifCodes/Ecommerce-Microservice.git>
 cd <Ecommerce-Microservice>
```

### 2. Start Eureka Server
Navigate to the `Service-registry` directory and start Eureka Server:
```sh
cd Service-registry
mvn spring-boot:run
```
Verify that Eureka is running at:
```
http://localhost:8761/
```

### 3. Start Microservices
Open new terminal windows for each service and run:

#### Start API Gateway
```sh
cd api-gateway
mvn spring-boot:run
```

#### Start Order Service
```sh
cd order-service
mvn spring-boot:run
```

#### Start Product Service
```sh
cd product-service
mvn spring-boot:run
```

### 4. Verify Running Services
Once all services are started, check Eureka Dashboard:
```
http://localhost:8761/
```
The registered services should appear here.

### 5. Testing the APIs
Once the services are running, you can test them:
- **Test API Gateway:**
  ```sh
  curl -X GET http://localhost:8084/products/abc
  ```
- **Test Order Service Directly:**
  ```sh
  curl -X GET http://localhost:8082/orders/123
  ```
- **Test Product Service Directly:**
  ```sh
  curl -X GET http://localhost:8081/products/abc
  ```

### 6. Stopping the Services
To stop the services, use `CTRL + C` in each terminal window.

## Running Services with Docker
### 1. Build the Services
Run the following command to build the services locally:
```sh
mvn clean install -DskipTests
```

### 2. Start the Microservices Using Docker Compose
```sh
docker-compose up --build -d
```
This will start the following services:
- **Eureka Server** (Service Registry) – `http://localhost:8761/`
- **API Gateway** – `http://localhost:8084/`
- **Order Service** – `http://localhost:8082/`
- **Product Service** – `http://localhost:8081/`

### 3. Verify Running Containers
Run the following command to check if all containers are running:
```sh
docker ps
```

### 4. Stopping the Services
To stop all running services, use:
```sh
docker-compose down
```

## Environment Setup
Make sure the following environment variables are configured in each service's `application.properties` or `application.yml` file:

**Eureka Configuration (For All Services)**
```properties
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
server.port=808X
```

**Spring Boot Configuration (Example for Product Service)**
```properties
server.port=8081
server.address=0.0.0.0
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

## Troubleshooting
If you face issues, check the logs:
```sh
docker-compose logs <service_name>
```
Example:
```sh
docker-compose logs api-gateway
```

For local runs, check each service’s logs:
```sh
tail -f logs/spring.log
```

If any service fails to start, restart it:
```sh
mvn spring-boot:run
```

