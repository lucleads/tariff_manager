# Build stage
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN apk update && apk add maven && mvn clean package -DskipTests && apk cache clean

# Runtime stage
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine
WORKDIR /home/app
COPY --from=build /home/app/target/*.jar /usr/local/lib/spring.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/spring.jar"]
