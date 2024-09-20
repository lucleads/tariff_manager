# Build stage
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine AS build

# Establecer directorio de trabajo
WORKDIR /home/app

# Copiar los archivos del proyecto
COPY pom.xml .
COPY src ./src

# Instalar Maven y compilar el proyecto
RUN apk update && apk add maven && mvn clean package -DskipTests

# Runtime stage
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine

# Establecer directorio de trabajo en el nuevo contenedor
WORKDIR /home/app

# Copiar el JAR generado desde la etapa de build
COPY --from=build /home/app/target/*.jar /usr/local/lib/spring.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/usr/local/lib/spring.jar"]
