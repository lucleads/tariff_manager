Microservice to manage product rates in certain time ranges.

# Technical

### Microservice architecture

###### [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)

###### [Command Query Responsibility Segregation (CQRS)](https://learn.microsoft.com/es-es/azure/architecture/patterns/cqrs)

##### [Domain-driven design (DDD)](https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/ddd-oriented-microservice)

### API standard

###### [Zalando RESTful API and Event Guidelines](https://opensource.zalando.com/restful-api-guidelines/)

### Glossary

**Tariff:** Product rate in a specific date

**Find:** Should find a result or throw an exception

**Search:** Should find a result or return empty

## Installation

### Pre-requisites

To be able to start development, make sure that you have any of the following prerequisites installed:

- Docker
- Maven
- Java 21

### Environment configuration

The default service port mapping is:

| Service | Port |
|---------|------|
| Spring  | 8080 |

The default ports can be overwritten on the **.env** file.


