
mvn clean install

mvn spring-boot:run


java -jar target/mywebserviceapp-0.0.1-SNAPSHOT.jar

This solution is a full-stack service that retrieves the historical Bitcoin prices between a user-specified start and end date. The service highlights the highest and lowest prices during the specified period and supports multiple currencies using real-time conversion rates.

The service adheres to SOLID, 12 Factor App, and HATEOAS principles and implements TDD/BDD practices. Sensitive information like API keys is securely stored.

Components Breakdown:
    Frontend (React.js or Vanilla JS with HTML)
    Backend (Java with Spring Boot): REST API with offline caching.
    Offline Mode: Fallback mechanism using cached data or mock data.
    CI/CD Pipeline (Jenkins): Automated build and deploy process.
    Dockerization: Containerized deployment for portability.
    TDD/BDD: Unit and behavior tests.
    Security: API key encryption and sensitive data protection.


Design Pattern Used 
1) Service layer Pattern
2) MVC Pattern
3) Strategy  Pattern



Step-by-Step Implementation:
1. Frontend (React.js )
The frontend collects the start date, end date, and currency inputs from the user, and communicates with the backend service via AJAX/Fetch calls. The results are displayed in a table format.

2. Backend (Spring Boot)
Controller: The controller handles incoming requests and returns JSON data.
Service Layer: Business logic is implemented here. It interacts with the Coindesk API to fetch historical data and calculates the highest and lowest prices.

3.  Offline Mode (Fallback)
Use a cache mechanism to store the last successful API response.
If the Coindesk API is down, return cached data.

4. CI/CD Pipeline (Jenkins)
Build: Compile the Java service and run tests.
Dockerize: Create a Docker image for the service.
Deploy: Run the Docker container on the local machine.

5. Dockerfile
Create a Docker image for portability and deployment.

6. TDD & BDD
TDD: Write unit tests using JUnit for service classes.
BDD: Use Cucumber for end-to-end testing.

7. Documentation
Swagger Documentation: Accessible at /swagger-ui.html.
README.md: Contains the following sections:
Project description
    Setup instructions (build, test, run)
    Sequence diagram or flowchart using draw.io
    Explanation of design patterns and best practices used.
    Build & Deploy Summary
    This service is production-ready, supporting both online and offline modes. The implementation adheres to best practices in security, performance, and optimization, ensuring smooth deployments via Jenkins and Docker.