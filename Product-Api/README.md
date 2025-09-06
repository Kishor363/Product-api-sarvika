 Product API – Spring Boot RESTful Application


 Project Overview

Product API is a RESTful service developed using Spring Boot that provides full CRUD functionality for a product catalog. The API supports creation, retrieval, updating, and deletion of products while enforcing validation rules, error handling.  

The application is designed to work with relational databases like MySQL , and uses H2 in-memory database for testing. API endpoints are documented using OpenAPI for easy visualization and testing.

---

 Key Features

- Full CRUD operations for `Product` resource  
- Validation of input data using Bean Validation (JSR 380)  
- Global exception handling with @RestControllerAdvice  
- Integration with relational databases MySQL  
- In-memory database support (H2) for testing  
- Pagination and sorting support using Spring Data JPA 
- API documentation with OpenAPI  
- Logging with SLF4J and Spring Boot Logging  
- Optional authentication with Spring Security  



 Technology Stack

| Layer          | Technology/Library                     
|----------------|---------------------------------------
| Language       | Java 17+                               
| Framework      | Spring Boot 3+                          
| Persistence    | Spring Data JPA,             
| Database       | MySQL, H2 (test) 
| Validation     | Bean Validation (JSR 380)             
| API Docs       | OpenAPI                      
| Build Tool     | Maven                         
| Testing        | JUnit 5, Mockito                        


