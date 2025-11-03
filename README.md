# CommerceHub

Commercehub is centralized SaaS platform that enables vendors to manage their shops and branches while allowing users to place and track orders seamlessly — built using Spring Boot microservices, Docker, Kubernetes, and Jenkins for scalability and automation.  
This project demonstrates a modular architecture with multiple services: order management, inventory, payment, store, branch, etc., all coordinated via service discovery, Docker, Kubernetes, and Jenkins.

## Architecutre Diagram

![image alt](https://github.com/AdityaZaware1/CommerceHub/blob/e6e1a158eceb02a69b915f1450e330b9b786f521/Commerhub.png)

## 📦 Services

| Service               | Description                                                                               |
|-----------------------|-------------------------------------------------------------------------------------------|
| **Eureka‑Server**     | Service registry for all microservices.                                                   |
| **Access‑Service**    | Authentication/authorization gateway (if implemented) or gateway to user/role management. |
| **Branch‑Service**    | Manages branches (locations) of stores.                                                   |
| **Store‑Service**     | Manages store entities (merchants) able to sell.                                          |
| **Inventory‑Service** | Manages product/inventory data for stores.                                                |
| **Order‑Service**     | Processes customer orders.                                                                |
| **Payment‑Service**   | Handles payments for orders.                                                              |
| **apis**              | Common API definitions/schema (if applicable).                                            |

## 🖥 Technology Stack

- **Backend:** Spring Boot (Java)  
- **Architecture:** Microservices (independent deployment per service)  
- **Service Discovery:** Netflix Eureka  
- **Communication:** REST APIs using Feign clients  
- **Database:** MySQL (each service may use its own schema)  
- **Containerization:** Docker  
- **Orchestration:** Kubernetes (K8s)  
- **CI/CD Pipeline:** Jenkins  
- **Version Control:** GitHub  
- **Monitoring & Resilience:** Resilience4j, Actuator  

## 🚀 Getting Started

### Prerequisites  
- Java 17+  
- Docker & Docker Compose  
- Kubernetes cluster (Minikube, Docker Desktop, or cloud provider)  
- Jenkins installed and configured  
- MySQL database (Docker or external)

### Setup & Run (Locally)

1. Clone the repository:  
   ```bash
   git clone https://github.com/AdityaZaware1/CommerceHub.git
   cd CommerceHub
   ```

2. Build the services:  
   ```bash
   cd apis
   mvn clean install -DskipTests
   ```

3. Start using Docker Compose:  
   ```bash
   docker-compose up --build
   ```

4. Access key services:  
   - Eureka Dashboard → [http://localhost:8761](http://localhost:8761)  
   - phpMyAdmin → [http://localhost:8080](http://localhost:8080) (if configured)
   - jenkins -> [http://localhost:8081](http://localhost:8080)

### 🧩 Kubernetes Deployment

To deploy all services on Kubernetes:

1. Build and push Docker images to Docker Hub:  
   ```bash
   docker build -t <username>/<service-name>:latest .
   docker push <username>/<service-name>:latest
   ```

2. Apply manifests from your `k8s/` folder:  
   ```bash
   kubectl apply -f k8s/
   ```

3. Verify all pods and services are running:  
   ```bash
   kubectl get pods
   kubectl get svc
   ```

4. Access the Eureka and API Gateway using NodePort or Ingress as configured.

### ⚙️ Jenkins CI/CD Pipeline

Jenkins automates build and deployment:

- **Stages:**
  1. **Checkout Code** → Pulls code from GitHub  
  2. **Build with Maven** → Builds each microservice  
  3. **Build Docker Images** → Creates Docker images for each service  
  4. **Push to Docker Hub**  
  5. **Deploy to Kubernetes** → Uses `kubectl apply` for deployment  

- Example Jenkinsfile excerpt:
  ```groovy
  pipeline {
      agent any

      tools {
          maven 'maven'
          dockerTool 'docker'
      }

      stages {
          stage('Checkout code') {
              steps {
                  git branch: 'master', url: 'https://github.com/AdityaZaware1/CommerceHub'
              }
          }

          stage('Build Maven') {
              steps {
                  dir('apis') {
                      sh 'mvn clean install -DskipTests'
                  }
              }
          }

          stage('Build Docker Images') {
              steps {
                  sh 'docker build -t adityazaware/<service-name> .'
              }
          }

          stage('Deploy to Kubernetes') {
              steps {
                  sh 'kubectl apply -f k8s/'
              }
          }
      }
  }
  ```

## 🧭 Project Structure

```
CommerceHub/
 ├─ Eureka‑Server/
 ├─ Access‑Service/
 ├─ Branch‑Service/
 ├─ Store‑Service/
 ├─ Inventory‑Service/
 ├─ Order‑Service/
 ├─ Payment‑Service/
 ├─ apis/
 ├─ docker‑compose.yml
 ├─ Jenkinsfile
 ├─ k8s/
```

Each microservice has its own `pom.xml`, Dockerfile, and `application.yml` for configuration.

## 🎯 Features

- Store and Branch Management  
- Product and Inventory Handling  
- Order Creation and Tracking  
- Payment Management  
- Centralized Service Discovery  
- Containerized Deployment  
- CI/CD Pipeline with Jenkins  
- Kubernetes for Scalability and Orchestration 
- Email Notification
- Jwt Authentication with spring security 

## 🔧 Roadmap

- Add centralized logging (ELK Stack)  
- Integrate Kafka for asynchronous events  
- Secure services with Keycloak or OAuth2  
- Add API Gateway (Spring Cloud Gateway)  


## 📝 Contributing

Contributions are welcome!  
1. Fork the repo  
2. Create a new branch: `git checkout -b feature/YourFeature`  
3. Commit and push changes  
4. Submit a Pull Request

## 📄 License

Specify your license here (MIT / Apache 2.0).

---

> Maintained by [Aditya Zaware](https://github.com/AdityaZaware1)
