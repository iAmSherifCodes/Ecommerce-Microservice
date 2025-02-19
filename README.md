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

# PRODUCT SERVICE

## Create Product
- This endpoint creates a new product.
-
POST (localhost:8084/product/add)


### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
```json
    {
      "name": "Samsung A21",
      "description" : "Samsung A21 series. SnapDragon series",
      "price": 30000
    }
```

### Sample Response Body
```json
    {
      "id":"545df54d-6bd6-4847-90d2-157796b40e4f",
      "name":"Samsung A21",
      "description":"Samsung A21 series. SnapDragon series",
      "price":30000
    }
```

## Get Product
- This endpoint gets in a Product by its Id.
-
GET (localhost:8084/product/{ID})

### Sample Request Header
    'Content-Type: application/json'

### Sample Response Body
```json
    {
        "id": "545df54d-6bd6-4847-90d2-157796b40e4f",
        "name": "Samsung A21",
        "description": "Samsung A21 series. SnapDragon series",
        "price": 30000
    }
```


## Get All Products
- This endpoint retrieves products.
  POST (localhost:8084/product/)

### Sample Request Header
    'Content-Type: application/json'


### Sample Response Body
```json
    [
        {
            "id": "545df54d-6bd6-4847-90d2-157796b40e4f",
            "name": "Samsung A21",
            "description": "Samsung A21 series. SnapDragon series",
            "price": 30000
        }
    ]
```

## Update Product
- This endpoint updates an existing product.
-
PUT (localhost:8084/product/update)


### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
```json
    {
      "id": "5a3db4f5-c5f5-4509-abb5-aa2107bf0ad0",
      "name": "Samsung A21",
      "description": "Samsung A21 series. SnapDragon series",
      "price": 35000
    }
```

### Sample Response Body
```json
    {
      "id": "5a3db4f5-c5f5-4509-abb5-aa2107bf0ad0",
      "name": "Samsung A21",
      "description": "Samsung A21 series. SnapDragon series",
      "price": 35000
    }
```

## Delete Product
- This endpoint deletes a product by its ID.
-
POST (localhost:8084/product/id)


### Sample Request Header
    'Content-Type: application/json'


### Sample Response Body
     Product deleted successfully


# ORDER API

## Create Order
- This endpoint creates a new order.
  POST (localhost:8084/order/add)

### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
```json
    {
        "productId": "545df54d-6bd6-4847-90d2-157796b40e4f",
        "quantity": 2,
        "totalPrice": 60000,
        "status": "PENDING"
    }
```

### Sample Response Body
```json
    {
        "id": "f01a5b3f-72f7-49eb-8974-1a1640990dfd",
        "productId": "545df54d-6bd6-4847-90d2-157796b40e4f",
        "quantity": 2,
        "totalPrice": 60000,
        "status": "PENDING",
        "productName": "Samsung A21",
        "productDescription": "Samsung A21 series. SnapDragon series",
        "productPrice": 30000
    }
```

## Get Orders
- This endpoint retrieves orders.
  POST (localhost:8084/order/)

### Sample Request Header
    'Content-Type: application/json'

### Sample Response Body
```json
    [
        {
            "id": "f01a5b3f-72f7-49eb-8974-1a1640990dfd",
            "productId": "545df54d-6bd6-4847-90d2-157796b40e4f",
            "quantity": 2,
            "totalPrice": 60000,
            "status": "PENDING",
            "productName": "Samsung A21",
            "productDescription": "Samsung A21 series. SnapDragon series",
            "productPrice": 30000
        }
    ]
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

